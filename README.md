# Sorting Tool CLI

A Java application for sorting different data types with support for file/console input and file/console output.
This project was completed as part of the [Hyperskill](https://hyperskill.org/projects/45) educational project.

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)
![Status](https://img.shields.io/badge/Status-Complete-brightgreen)
![Architecture](https://img.shields.io/badge/Architecture-OOP-blue)

## Project Structure

```
src/
├── data/
│   ├── in.txt          # Example input file
│   └── out.txt         # Example output file
├── FileManager.java    # File I/O management
├── InputHandler.java   # Console input handling
├── Main.java          # Main application class
└── SortManager.java    # Sorting algorithms and logic
```

## Features

- **Multiple Data Types**: Supports sorting of `long`, `word`, and `line` data types
- **Sorting Modes**: 
  - `natural` - natural ordering
  - `byCount` - sort by frequency of occurrence
- **Flexible I/O**: Read from console or file, write to console or file
- **Command-line Interface**: Configurable via command-line arguments

## Building and Running

1. Compile all Java files:
   ```bash
   javac src/*.java
   ```

2. Run from the src directory:
   ```bash
   cd src
   java Main [options]
   ```

## Usage
### Basic Syntax
```bash
java Main [options]
```

### Command-line Options

| Option | Description | Accepted Values |
|--------|-------------|----------------|
| `-dataType` | Type of data to process | `long`, `word`, `line` |
| `-sortingType` | Sorting algorithm to use | `natural`, `byCount` |
| `-inputFile` | Path to input file (optional) | Any valid file path |
| `-outputFile` | Path to output file (optional) | Any valid file path |

### Examples

1. **Sort words from console input (natural order):**
   ```bash
   java Main -dataType word -sortingType natural
   ```

2. **Sort numbers from a file by frequency:**
   ```bash
   java Main -dataType long -sortingType byCount -inputFile in.txt
   ```

3. **Sort lines from a file and save results:**
   ```bash
   java Main -dataType line -sortingType natural -inputFile in.txt -outputFile out.txt
   ```

4. **All options combined:**
   ```bash
   java Main -dataType word -sortingType byCount -inputFile input.txt -outputFile output.txt
   ```

## Data Types

- **`long`**: Integer numbers (e.g., `123`, `-456`, `789`)
- **`word`**: Sequences of characters separated by whitespace (e.g., `hello`, `world`)
- **`line`**: Complete lines of text including spaces

## Sorting Types

- **`natural`**: Sorts elements in natural ascending order
- **`byCount`**: Sorts elements by frequency of occurrence (least frequent first), with ties broken by natural order

## Input/Output Behavior

- When no `-inputFile` is specified, reads from standard input (console)
- When no `-outputFile` is specified, writes to standard output (console)
- Invalid parameters are skipped with appropriate warnings
- Missing required values for parameters display error messages

## Example Workflow

1. Create input file `data/in.txt`:
   ```
   banana      apple
         cherry             apple
   banana
                apple
   ```

2. Run the program:
   ```bash
   java Main -dataType word -sortingType byCount -inputFile in.txt -outputFile out.txt
   ```

3. Check output in `data/out.txt`:
   ```
   Total words: 6.
   cherry: 1 time(s), 17%
   banana: 2 time(s), 33%
   apple: 3 time(s), 50%
   ```

## Error Handling

- Invalid parameters: Warning message, parameter skipped
- Missing file paths: Appropriate error message
- Invalid data types: Type-specific validation
- File not found: Clear error handling
