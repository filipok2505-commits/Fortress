package com.example.crypto.domain.model

data class EncryptedData (
    val encryptedBytes: ByteArray,
    val initializationVector: ByteArray
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EncryptedData

        if (!encryptedBytes.contentEquals(other.encryptedBytes)) return false
        if (!initializationVector.contentEquals(other.initializationVector)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = encryptedBytes.contentHashCode()
        result = 31 * result + initializationVector.contentHashCode()
        return result
    }

    override fun toString(): String {
        return "EncryptedData(ciphertext=${encryptedBytes.toHex()}, iv=${initializationVector.toHex()})"
    }
}

private fun ByteArray.toHex(): String = joinToString("") { "%02x".format(it) }