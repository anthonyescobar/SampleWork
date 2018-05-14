'''
Homework 1 MazeProblem Formalization:
MazeProblems represent 2D pathfinding problems, as programmatically
formalized via:

=== Mazes ===
Represented as a list of strings in which:
  X = impassable wall
  * = the initial state
  . = open cells (movement cost of 1)
  M = mud tiles (movement cost of 3)
  G = goal states (movement cost of 1)
All valid mazes have:
  - Exactly 1 initial state
  - At least 1 goal state
  - A border of walls (plus possibly other walls)
[!] Note: for HW1, a valid maze may *not* have a solution!
(We'll ignore invalid maze states as possible inputs, for simplicity)

Maze elements are indexed starting at (0, 0) [top left of maze]. E.g.,
["XXXXX", "X..GX", "X.M.X", "X*..X", "XXXXX"] is interpretable as:
  01234
0 XXXXX
1 X..GX
2 X.M.X
3 X*..X
4 XXXXX

=== States ===
Representing the position of the agent, as tuples in which:
(x, y) = (col, row)
(0, 0) is located at the top left corner; Right is +x, and Down is +y

=== Actions ===
Representing the allowable Up, Down, Left, and Right movement capabilities
of the agent in the 2D Maze; we'll simply use string representations:
"U", "D", "L", "R"

=== Transitions ===
Given some state s, the transitions will be represented as a list of tuples
of the format:
[(action1, cost_of_action1, result(action1, s)), ...]
For example, if an agent is at state (1, 1), and can only move right and down
into clear tiles (.), then the transitions for that s = (1, 1) would be:
[("R", 1, (2, 1)), ("D", 1, (1, 2))]
'''
class MazeProblem:
    # Static costMap for maze components and the cost to move onto them
    # Any component not listed assumed to have a cost of 1
    costMap = {"M": 3}

    # MazeProblem Constructor:
    # Constructs a new pathfinding problem from a maze, described above
    def __init__(self, maze):
        self.maze = maze
        self.initial = None
        self.goals = []

        for r in list(enumerate(maze)):
            for c in list(enumerate(r[1])):
                state = (c[0], r[0])
                if c[1] is "*":
                    self.initial = state
                if c[1] is "G":
                    self.goals.append(state)

    # goalTest is parameterized by a state, and
    # returns True if the given state is a goal, False otherwise
    def goalTest(self, state):
        for goal in self.goals:
            if state == goal:
                return True
        return False

    # Implements the Manhattan Distance Heuristic, which (given a state)
    # provides the cell-distance to the nearest goal state
    def heuristic(self, state):
        nearest = len(self.maze) * len(self.maze[0])
        for goal in self.goals:
            heur = abs(goal[0] - state[0]) + abs(goal[1] - state[1])
            nearest = heur if nearest > heur else nearest
        # print(heur)
        return heur

    # transitions returns a list of tuples in the format:
    # [(action1, cost_of_action1, result(action1, s), ...]
    # corresponding to allowable actions of the given state, as well
    # as the next state the action leads to
    def transitions(self, state):
        transitions = []
        up = (state[0],state[1]-1)
        if self.maze[up[1]][up[0]] != "X":
            transitions.append( ("U", self.cost(state), up))

        down = (state[0],state[1]+1)
        if self.maze[down[1]][down[0]] != "X":
            transitions.append( ("D", self.cost(state), down))

        left = (state[0]-1,state[1])
        if self.maze[left[1]][left[0]] != "X":
            transitions.append( ("L", self.cost(state), left))

        right = (state[0]+1,state[1])
        if self.maze[right[1]][right[0]] != "X":
            transitions.append( ("R", self.cost(state), right))
        return transitions

    # cost returns the cost of moving onto the given state, and employs
    # the MazeProblem's costMap
    def cost(self, state):
        cm = MazeProblem.costMap
        cell = self.maze[state[1]][state[0]]
        return cm[cell] if cell in cm else 1

    # solnTest will return a tuple of the format (cost, isSoln) where:
    # cost = the total cost of the solution,
    # isSoln = true if the given sequence of actions of the format:
    # [a1, a2, ...] successfully navigates to a goal state from the initial state
    # If NOT a solution, return a cost of -1
    def solnTest(self, soln):
        trans = {"U": (0, -1), "D": (0, 1), "L": (-1, 0), "R": (1, 0)}
        s = self.initial
        tc = 0
        for m in soln:
            s = (s[0] + trans[m][0], s[1] + trans[m][1])
            tc += self.cost(s)
            if self.maze[s[1]][s[0]] == "X":
                return (-1, False)
        return (tc, self.goalTest(s))
