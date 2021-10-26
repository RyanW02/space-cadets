This is my implementation of Bare Bones.

## Features
- If statements (no else yet 🙁)
- While loops
- Procedures
- Print strings, with formatting

## Example Code
The following code loops forever, asking the user for 2 numbers to add.
It can also be found in the [examples directory](./examples/procedures).
```
procedure add
    copy one X;
    copy two Y;
    clear result;

    while Y != 0 do;
        incr X;
        decr Y;
    end;
    copy X result;
end

clear one;
clear two;

while 1 = 1 do;
    print "Input the first number, or -1 to quit:";
    input one;
    if one < 0;
        quit;
    end;

    print "Input the second number, or -1 to quit:";
    input two;
    if two < 0;
        quit;
    end;

    call add;
    print "{one} + {two} = {result}";
    print "";
end;
```

## Running code
First, write your program to a file.

Then, compile the interpreter by executing `gradle build` in this directory, if you have gradle installed.

Alternatively, run `../gradlew build` from this directory to use the provided gradle distribution.

Finally, you may also run `./gradlew :bare-bones:build` from the parent / project root directory.

The compiled jar will be produced in `space-cadets/bare-bones/build/libs/bare-bones.jar`.

A pre-compiled jar is also available [here](https://github.com/RyanW02/space-cadets/releases).

You can then execute your program using `java -jar build/libs/bare-bones.jar path/to/your/program` from this directory.

## Syntax
A lot of things are optional, such as semicolons and whitespace. Anything written after the last required token will be
ignored too! e.g. `if x == 1 hello :)!` will still run fine.

## Instruction set
### Calc
Performs an arithmetic operation and stores the value. See [arithmetic operators](#arithmetic-operators) for available operators.
```
calc x = 3 + 4;
```

### Call
Calls a previously defined function (must be defined above the `call`).
```
procedure my_procedure
    quit;
end

call my_procedure;
```

### Clear
Sets the value of a variable to 0
```
clear X;
```

### Copy
Copies the value of 1 variable to another
```
incr X;
copy X Y;
print Y;
// 1
```

### Decrement
Decreases the value of a variable by 1
```
decr X;
print X;
// -1
```

### If
Conditional statement. Can take constants or variables on either side. See [operators](#operators) for available operators.
```
incr X;
if X == 3;
    print "The value of X is 3.";
end;
```

### Increment
Increments the value of a variable by 1
```
incr X;
print X;
// 1
```

### Input
Takes an input from the command line and stores it in a variable.
```
input X;
// User enters 300
print X;
// 300
```

### Print
Prints a string / variable to the terminal.
```
incr X;

print X;
// 1

print "The value of X is {X}.";
// The value of X is 1.
```

### Procedure
Defines a subroutine that can be called later in the program. See [call](#call) for an example.

### Quit
Quits the program.
```
quit;
```

### While
Repeats the code block until the condition is true. Can take constants or variables on either side. See [operators](#operators) for available operators.
```
clear X;
while X < 3;
    incr X;
    print "X is less than 3.";
end;
```

## Operators
The following operators are available for use in conditional statements:
- `==`, `=`, `eq`
- `!=`, `not`, `ne`,
- `>`, `gt`
- `>=`
- `<`, `lt`
- `<=`

## Arithmetic Operators
The following operators are available for use in arithmetic operations:
- `+`
- `-`
- `*`
- `/`
- `^`