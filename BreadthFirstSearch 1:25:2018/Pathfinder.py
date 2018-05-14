'''
Name: Anthony Escobar

The Pathfinder class is responsible for finding a solution (i.e., a
sequence of actions) that takes the agent from the initial state to the
optimal goal state.

This task is done in the Pathfinder.solve method, as parameterized
by a maze pathfinding problem, and is aided by the SearchTreeNode DS.
'''

from MazeProblem import MazeProblem
from SearchTreeNode import SearchTreeNode
import unittest

class Pathfinder:

    # solve is parameterized by a maze pathfinding problem
    # (see MazeProblem.py and unit tests below), and will
    # return a list of actions that solves that problem. An
    # example returned list might look like:
    # ["U", "R", "R", "U"]
    def solve(problem):
        # print("test")
        # print(MazeProblem.transitions(problem, problem.initial))
        i = 0
        goalFound = False
        solveArray = []
        nodeArray = [SearchTreeNode(problem.initial,None,None)]
        # find transitions for SearchTreeNode
        while not goalFound:
            print(i)
            for tup in MazeProblem.transitions(problem, nodeArray[i].state):
                nodeArray[i].children.append(SearchTreeNode(tup[1],tup[0],nodeArray[i]))
            # print(nodeArray[i].children)
            for stNode in nodeArray[i].children:
                nodeArray.append(stNode)
                # print(stNode.state)
                # print(i)
                goalFound = MazeProblem.goalTest(problem,stNode.state)
                goalIndex = i
                if goalFound:
                    break
            i += 1
        # print(nodeArray[goalIndex].state)
        if goalFound:
            current = nodeArray[len(nodeArray)-1]
            while current.parent != None:
                # print(current.state)
                solveArray.insert(0,current.action)
                current = current.parent
        # print(solveArray)
        return solveArray

class PathfinderTests(unittest.TestCase):
    def test_maze1(self):
        maze = ["XXXXX", "X..GX", "X...X", "X*..X", "XXXXX"]
        problem = MazeProblem(maze)
        soln = Pathfinder.solve(problem)
        solnTest = problem.solnTest(soln)
        self.assertTrue(solnTest[1])
        self.assertEqual(solnTest[0], 4)

    def test_maze2(self):
        maze = ["XXXXX", "XG..X", "XX..X", "X*..X", "XXXXX"]
        problem = MazeProblem(maze)
        soln = Pathfinder.solve(problem)
        solnTest = problem.solnTest(soln)
        self.assertTrue(solnTest[1])
        self.assertEqual(solnTest[0], 4)

    def test_maze3(self):
        maze = ["XXXXXXXXXX", "XG.......X", "XX.......X", "X.......*X", "XXXXXXXXXX"]
        problem = MazeProblem(maze)
        soln = Pathfinder.solve(problem)
        solnTest = problem.solnTest(soln)
        self.assertTrue(solnTest[1])
        self.assertEqual(solnTest[0], 9)

    def test_maze4(self):
        maze = ["XXXXXXXXXX", "XG......GX", "XX.......X", "X.......*X", "XXXXXXXXXX"]
        problem = MazeProblem(maze)
        soln = Pathfinder.solve(problem)
        solnTest = problem.solnTest(soln)
        self.assertTrue(solnTest[1])
        self.assertEqual(solnTest[0], 2)

    # def test_maze5(self):
    #     maze = ["XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "X...................................................................................................GX", "X....................................................................................................X", "X*...................................................................................................X", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"]
    #     problem = MazeProblem(maze)
    #     soln = Pathfinder.solve(problem)
    #     solnTest = problem.solnTest(soln)
    #     self.assertTrue(solnTest[1])
    #     self.assertEqual(solnTest[0], 101)

    # TODO: Add more unit tests!

if __name__ == '__main__':
    unittest.main()
