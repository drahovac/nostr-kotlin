package com.bobmitchigan

internal data class EventEntityTestBuilder(
    val id: String = "id",
    val pubkey: String = "pub",
    val created_at: Long = 0,
    val kind: Int = 1,
    val content: String? = "Content",
    val sig: String = "Signature",
    val tags: List<List<String>> = listOf()
) {
    fun build() = EventEntity(id, pubkey, created_at, kind, content, sig, tags)
}
