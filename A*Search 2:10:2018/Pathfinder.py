'''
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

    @staticmethod
    def solve(problem):
        # print('testing in progress...')
        # print(problem.initial)
        # print(problem.goals[0])
        i = 0
        nodeCount = 0
        solArray = []
        goalFound = False
        pStack = [SearchTreeNode(problem.initial,None,None,0,problem.heuristic(problem.initial))]
        while not goalFound:
            try:
                solArray.append(pStack.pop())
                # nodeCount += 1
                # print(nodeCount)
            except IndexError:
                return []
            # print("-")
            # print(solArray[i].state)
            if problem.goalTest(solArray[i].state):
                goalFound = True
                actions = []
                current = solArray[len(solArray)-1]
                while current.parent != None:
                    # print(current.state)
                    actions.insert(0,current.action)
                    current = current.parent
                # print(actions)
                return actions
            for tranNode in problem.transitions(solArray[i].state):
                # insert STN into stack based on TotalPlusHeur
                stn = SearchTreeNode(tranNode[2],tranNode[0],solArray[i],solArray[i].totalCost+tranNode[1],problem.heuristic(tranNode[2]))
                # nodeCount += 1
                # print(nodeCount)
                if stn not in solArray:
                    if len(pStack) == 0:
                        pStack.append(stn)
                    else:
                        j = 0
                        for n in pStack:
                            if stn.__gt__(n):
                                pStack.insert(j,stn)
                                break
                            else:
                                j+=1
                        if j == len(pStack):
                            pStack.append(stn)
            i+=1
        return []

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
        maze = ["XXXXX", "X..GX", "X.MMX", "X*..X", "XXXXX"]
        problem = MazeProblem(maze)
        soln = Pathfinder.solve(problem)
        solnTest = problem.solnTest(soln)
        self.assertTrue(solnTest[1])
        self.assertEqual(solnTest[0], 4)

    def test_maze4(self):
        maze = ["XXXXXX", "X....X", "X*.XXX", "X..XGX", "XXXXXX"]
        problem = MazeProblem(maze)
        soln = Pathfinder.solve(problem)
        self.assertFalse(soln)

    def test_maze5(self):
        maze = ["XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", "X...................................................................................................GX", "X....................................................................................................X", "X*...................................................................................................X", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"]
        problem = MazeProblem(maze)
        soln = Pathfinder.solve(problem)
        solnTest = problem.solnTest(soln)
        self.assertTrue(solnTest[1])
        self.assertEqual(solnTest[0], 101)


if __name__ == '__main__':
    unittest.main()
