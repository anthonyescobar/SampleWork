'''
SearchTreeNodes contain the following information for BFS:

=== state ===
The state represented by the node, a tuple:
(x, y) = (col, row)

=== action ===
The action used to transition from the parent to this node.
- The action's value is None if the initial state
- The action's value will be a string "U", "D", "L", "R" otherwise

=== parent ===
The parent of this node in the search tree.
- The parent's value is None if the initial state
- The parent's value is a reference to the parent node otherwise

=== children ===
References to all generated children of this node in the search tree

=== totalCost ===
The total cost of the path of actions that led to this particular state.
In the notes, we refer to this value as being evaluated through g(n)

=== heuristicCost ===
The heuristic estimate of cost to be incurred from this node to the
optimal solution
'''
class SearchTreeNode:

    def __init__(self, state, action, parent, totalCost, heuristicCost):
        self.state = state
        self.action = action
        self.parent = parent
        self.children = []
        self.totalCost = totalCost
        self.heuristicCost = heuristicCost


    def __eq__(self,other):
        if other == None:
            return False
        return self.state == other.state

    def __lt__(self,other):
        return self.TotalPlusHeur() < other.TotalPlusHeur()

    def __gt__(self,other):
        return self.TotalPlusHeur() > other.TotalPlusHeur()

    def TotalPlusHeur(self):
        return self.totalCost + self.heuristicCost
