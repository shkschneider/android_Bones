package me.shkschneider.cli.utils

import com.github.ajalt.clikt.output.TermUi.echo
import me.shkschneider.cli.Options

data class Author(val name: String, val email: String)
data class Commit(val hash: String, val message: String, val date: String? = null)
data class Remote(val name: String, val source: String)

object GitCommands {

    private const val HEAD = "HEAD"
    private const val SEPARATOR = ';'

    private val dir: String by lazy { dir() }
    private val remote: String get() = remotes().first().name

    fun ahead(local: String = branch(), parent: String = "${Options.remote}/$local"): List<Commit> {
        val ahead = mutableListOf<Commit>()
        exec("git log --format=%h$SEPARATOR%s $parent..$local") { result ->
            result.onFailure()
            result.onSuccess {
                it.forEach {
                    val (hash, message) = it.split(SEPARATOR)
                    ahead.add((Commit(hash, message)))
                }
            }
        }
        return ahead
    }

    fun author(): Author {
        var name: String? = null
        var email: String? = null
        exec("git config user.name") { result ->
            result.onFailure()
            result.onSuccess { name = it.first() }
        }
        exec("git config user.email") { result ->
            result.onFailure()
            result.onSuccess { email = it.first() }
        }
        return Author(
            requireNotNull(name) { "name" },
            requireNotNull(email) { "email" })
    }

    fun authors(): List<Author> {
        val authors = mutableListOf<Author>()
        exec("git log --format=%aN$SEPARATOR%aE") { result ->
            result.onFailure()
            result.onSuccess {
                it.forEach {
                    val (name, email) = it.split(SEPARATOR)
                    authors.add(
                        Author(
                            name.toLowerCase(),
                            email.toLowerCase()
                        )
                    )
                }
            }
        }
        return requireNotNull(authors.distinct().takeIf { it.isNotEmpty() }) { "authors" }
    }

    fun behind(local: String = branch(), parent: String = "${Options.remote}/$local"): List<Commit> {
        val behind = mutableListOf<Commit>()
        exec("git log --format=%h$SEPARATOR%s $local..$parent") { result ->
            result.onFailure()
            result.onSuccess {
                it.forEach {
                    val (hash, message) = it.split(SEPARATOR)
                    behind.add((Commit(hash, message)))
                }
            }
        }
        return behind
    }

    fun birth(): Commit {
        var birth: Commit? = null
        exec("git rev-list --max-parents=0 HEAD --format=%h$SEPARATOR%s$SEPARATOR%ci") { result ->
            result.onFailure()
            result.onSuccess {
                val (hash, message, date) = it.last()
                    .split(SEPARATOR)
                birth = Commit(hash, message, date)
            }
        }
        return requireNotNull(birth) { "birth" }
    }

    fun branch(ref: String = HEAD): String {
        var branch: String? = null
        exec("git rev-parse --verify --abbrev-ref $ref") { result ->
            result.onFailure()
            result.onSuccess { branch = it.first() }
        }
        return requireNotNull(branch) { "branch" }
    }

    fun commit(hash: String): Commit {
        var message: String? = null
        exec("git log -1 --format=%B $hash") { result ->
            result.onFailure()
            result.onSuccess { message = it.first() }
        }
        return Commit(
            hash,
            requireNotNull(message) { "message" })
    }

    fun dir(): String {
        var dir: String? = null
        exec("git rev-parse --absolute-git-dir") { result ->
            result.onFailure()
            result.onSuccess { dir = it.first() }
        }
        return requireNotNull(dir) { "dir" }
    }

    fun fetch(all: Boolean = false) {
        if (Options.verbose) echo("Fetching '" + (if (all) "all" else remote) + "'...")
        exec("git fetch" + if (all) " --all" else " $remote") { result ->
            result.onFailure()
        }
    }

    fun hash(ref: String = HEAD): String {
        var commit: String? = null
        exec("git rev-parse --verify --short $ref") { result ->
            result.onFailure()
            result.onSuccess { commit = it.first() }
        }
        return requireNotNull(commit) { "commit" }
    }

    fun head(tip: String = HEAD): Commit {
        var head: Commit? = null
        // git describe --tags --always --first-parent
        exec("git log -1 $tip --format=%h$SEPARATOR%s") { result ->
            result.onFailure()
            result.onSuccess {
                val (hash, message) = it.first()
                    .split(SEPARATOR)
                head = Commit(hash, message)
            }
        }
        return requireNotNull(head) { "head" }
    }

    fun pull(branch: String = branch(), rebase: Boolean = true): Boolean {
        var success: Boolean = false
        if (Options.verbose) echo("Pulling '$branch' from '$remote'...")
        exec("git pull" + (if (rebase) " --rebase" else "") + " $remote $branch") { result ->
            result.onFailure()
            result.onSuccess { success = true }
        }
        return success
    }

    fun push(branch: String = branch()) {
        if (Options.verbose) echo("Pushing '$branch' to '$remote'...")
        exec("git push $remote $branch") { result ->
            result.onFailure()
        }
    }

    fun rebasing(): Boolean {
        val merge = file(dir, "rebase-merge").exists()
        val apply = file(dir, "rebase-apply").exists()
        return merge || apply
    }

    fun remotes(): List<Remote> {
        val remotes: MutableList<Remote> = mutableListOf()
        exec("git remote -v") { result ->
            result.onFailure()
            result.onSuccess {
                it.forEach {
                    val (name, uri, _) = it.split(" ")
                    remotes.add(Remote(name, uri))
                }
            }
        }
        return requireNotNull(remotes.takeIf { it.size > 0 }) { "remotes" }
    }

    fun root(): String {
        var root: String? = null
        exec("git rev-parse --show-toplevel") { result ->
            result.onFailure()
            result.onSuccess { root = it.first() }
        }
        return requireNotNull(root) { "root" }
    }

    fun stashes(): List<Commit> {
        val stashes = mutableListOf<Commit>()
        exec("git stash list --format=%h$SEPARATOR%s") { result ->
            result.onFailure()
            result.onSuccess {
                it.forEach {
                    val hash =
                        it.takeWhile { it != SEPARATOR }
                    val message = it.drop(hash.length + 1)
                    stashes.add(Commit(hash, message))
                }
            }
        }
        return requireNotNull(stashes.takeIf { it.isNotEmpty() }) { "stashes" }
    }

    fun status(): List<String> {
        val status = mutableListOf<String>()
        exec("git status --porcelain") { result ->
            result.onFailure()
            result.onSuccess {
                it.forEach {
                    status.add(
                        if (it.contains(" -> ")) it.substringAfter(" -> ")
                        else it.drop(3)
                    )
                }
            }
        }
        return requireNotNull(status) { "status" }
    }

    fun version(): String {
        var version: String? = null
        exec("git --version") { result ->
            result.onFailure()
            result.onSuccess {
                version = it.first().filter { it.isDigit() || it == '.' }
            }
        }
        return requireNotNull(version) { "version" }
    }

}