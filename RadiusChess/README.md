
# RadiusChess

#### Prologue
Refer to RadiusChallenge.pdf for description of the problem.
This is a Java + Maven project built in IntelliJ. Test framework used is JUnit + Hamcrest + Mockito. Git history will show a TDD style of coding.

#### Manual
Download the jar file at RadiusChess/jar/RadiusChess.jar
Run with 'java -jar RadiusChess.jar'.

#### Description
The code is architected in an OOP paradigm. We have the following characters in the project.

(a) RadiusApp. This is the application driver which contains the static main function.

(b) Arena. Arena is an encapsulation of the board. Arena is where the attributes of a board are defined - this includes the dimensions of the board, list of squares that are ineligible (traps) and list of squares that can not be the start-square. Arena conforms to the ArenaInterface, and all concrete arenas derive from an AbstractArena base-class. FoneArena is our implementation for RadiusChallenge with dimensions 4x3, two traps at ((0,0), (0,2)) and two clays ((0,1), (3,0)).

(c) Role. Roles are the various characters in chess like King, Knight etc. Roles conform to the RoleInterface, and all concrete roles derive from an AbstractRole base-class. Role encapsulates the movement-logic of the various characters, and provides a method that when queried with current-position and board-dimension returns a list of squares that the character may move to in the next jump.

(d) Square. Square is a simple convenient way to represent a position on the arena. Square also provides some utility methods for checking whether a square is within the bounds of a board, and if a square is contained in a list-of-squares.

(e) Abacus. Abacus is the solver and the engine. Abacus is the engine that knows how to use the roles, arenas and squares. Abacus is also the solver that encapsulates the strategy of computation. Abacus conforms to an AbacusInterface, and AbacusEngine is our concrete implementation that uses dynamic-programming to solve the puzzle. Abacus delegates the task of instantiating Arenas and Roles to the corresponding factories.

(f) Here is a list of design-patterns that may be observed in the project. abstract-class, interface, inheritance, encapsulation, delegation, dependency-injection, factory, strategy, mock, streams, pipes, exceptions, immutability.

#### Remarks
(a) OOP design has been done in a way that we can easily extend the game to arbitrary-dimensions of boards, lists of squares/trays, or even fictious roles. However the 2D nature of the board is tightly coupled with the types. Hence if you wish to generalize the game to a 3D arena, major refactoring may be required.

(b) Roles may be enforced to be singletons. Computed results may be stored or cached, currently Abacus recomputes the solution on every query which is suboptimal.

-- eof --
