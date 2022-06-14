enum class Module {
    Web,
    Domain,
    Infrastructure;

    override fun toString(): String {
        return ":${name.toLowerCase()}"
    }
}