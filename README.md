# RSA Key Generation & Message Encryption Demo (Java)

Educational implementation demonstrating RSA public-key cryptography, including key pair generation, message encryption/decryption, and security best practices.

**Difficulty:** Intermediate  
**Technologies:** Java · Cryptography · Security · JCA/JCE

---

## Overview

This Java project demonstrates the **RSA public-key cryptosystem**, a foundational algorithm in modern cybersecurity. The implementation covers key generation, encryption, and decryption workflows while emphasizing educational clarity and security awareness. The project includes explanations of cryptographic concepts, security pitfalls, and ethical considerations.

---

## Features

- Generates **2048-bit RSA key pairs** using Java Crypto
- Encodes keys in **Base64** for readable display
- Encrypts plaintext messages with the public key
- Decrypts ciphertext with the private key
- Demonstrates proper key storage concepts
- Includes security best practices documentation

---

## What is RSA?

**RSA (Rivest–Shamir–Adleman)** is a public-key cryptosystem widely used for secure data transmission. It uses two keys: a **public key** (shared openly) for encryption, and a **private key** (kept secret) for decryption.

### How It Works (Simplified)

1. **Key Generation:** Choose two large prime numbers, compute their product (modulus), and derive public and private exponents.
2. **Encryption:** Message is converted to a number and raised to the public exponent, modulo the modulus.
3. **Decryption:** Ciphertext is raised to the private exponent, modulo the modulus, recovering the original message.

### Security Foundation

RSA's security relies on the **difficulty of factoring large numbers**. While multiplying two primes is easy, finding those primes from their product is computationally infeasible for sufficiently large numbers (2048+ bits).

### Textbook RSA Pitfalls

"Textbook RSA" (raw RSA without padding) is vulnerable to:

- **Deterministic output:** Same message always produces same ciphertext
- **Small message attacks:** Messages smaller than the modulus can be guessed
- **No integrity checking:** Ciphertext can be modified undetected

### Padding: OAEP

**Optimal Asymmetric Encryption Padding (OAEP)** adds randomness and structure to messages before encryption, preventing the attacks above. Modern RSA implementations always use OAEP or similar padding schemes.

---

## Sample Output

```
=== RSA Encryption Demo ===

[1] Generating 2048-bit RSA key pair...
    ✓ Key pair generated successfully

[2] Key Information:
    Public Key (Base64):
    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyXg...

    Private Key (Base64 - KEEP SECRET!):
    MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAo...

[3] Encrypting message:
    Original: "Hello, Secure World!"
    Encrypted (Base64): "QWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXoxMjM0NTY3ODk..."

[4] Decrypting with private key...
    Decrypted: "Hello, Secure World!"

[5] Verification:
    ✓ Original and decrypted messages match!
    ✓ Encryption/Decryption successful.

=== Security Notes ===
- Private key was never transmitted or exposed
- OAEP padding was used (PKCS#1 v2.1)
- Key size: 2048 bits (recommended minimum)
- Never hardcode keys in production code!
```

---

## Getting Started

### Prerequisites

- Java (JDK 8+)
- Maven or Gradle (see project for build configuration)

### Build & Run

Use the included Maven or Gradle configuration to build and run the demo. See the project source for exact commands.

---

## Learning Outcomes

- Understanding of public-key cryptography principles
- Practical experience with **Java Cryptography Architecture (JCA/JCE)**
- Awareness of common cryptographic vulnerabilities
- Knowledge of padding schemes and their importance
- Ethical considerations in security implementations

---

## Deliverables

- Java source with modular crypto operations
- Maven/Gradle build configuration
- JUnit tests for encryption round-trip
- Security documentation and best practices guide
- Sample key generation and usage examples

---

## Ethics & Best Practices

- Cryptography is a dual-use technology—protect privacy but respect laws
- **Never implement custom crypto algorithms;** use well-vetted libraries
- Key management is often the weakest link in cryptographic systems
- Always use padding schemes (OAEP, PKCS#1 v1.5) with RSA
- Report security vulnerabilities responsibly (ethical disclosure)
- Respect export control laws regarding cryptographic software

---

## License

See repository for license details.
