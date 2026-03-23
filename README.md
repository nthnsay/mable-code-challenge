# Mable Code Challenge

## Overview

This is a Kotlin command-line application that processes a batch of account transfers from CSV input.

The application:

* loads accounts and their starting balances from an accounts CSV
* loads transfer requests from a transfers CSV
* validates and processes each transfer
* reports successful and failed transfers
* outputs final account balances

---

## How to Run

### Run tests

```bash
./gradlew test
```

Windows (PowerShell):

```powershell
.\gradlew test
```

---

### Run application

```bash
./gradlew run --args="mable_account_balances.csv mable_transactions.csv"
```

Windows (PowerShell):

```powershell
.\gradlew run --args="mable_account_balances.csv mable_transactions.csv"
```

---

## Design

The application is structured into small, focused components:

* **Account**
  Encapsulates account state and validation.

* **TransferRequest**
  Represents an incoming transfer instruction.

* **TransferResult**
  Represents the outcome of processing a transfer.

* **TransferService**
  Contains business logic for validating and executing transfers.

* **TransferBatchProcessor**
  Processes a list of transfers and aggregates results.

* **CsvAccountParser / CsvTransferParser**
  Responsible for parsing CSV input into domain objects.

* **Main.kt**
  Thin entry point that wires everything together.

---

## Assumptions

* Account numbers must be 16-digit numeric values
* CSV files do not contain headers and follow the format provided in the challenge
* Monetary values are handled using `BigDecimal`
* Transfers that fail validation do not stop batch processing
* Failures are reported with a reason

---
