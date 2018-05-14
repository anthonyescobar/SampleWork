'''
Anthony Escobar

MazeKnowledgeBase.py

Specifies a simple, Conjunctive Normal Form Propositional
Logic Knowledge Base for use in Grid Maze pathfinding problems
with side-information.
'''
from MazeClause import MazeClause
import unittest

class MazeKnowledgeBase:

    def __init__ (self):
        self.clauses = set()

    # [!] Assumes that a clause is never added that causes the
    # KB to become inconsistent
    def tell (self, clause):
        if clause not in self.clauses:
            self.clauses.add(clause)
        return # clause

    # [!] Queries are always MazeClauses
    def ask (self, query):
        # kb = self
        query.contrad()
        # to deal with queries that gotta DeMorg 
        # print(type(query))
        # print(len(query.props))
        # if len(query.props) > 1:
        #     for q in query.props:
        #         print(str(q) + " " + str(query.getProp(q)))
        #         p = (q,query.getProp(q))
        #         self.tell(MazeClause(p))
        # else:
        #     self.tell(query)
                # self.tell(set(MazeClause((q, query[q]))))
        # else:
        #     self.tell(query)
        # kb.tell(query)
        # print(kb)
        self.tell(query)
        new = set()
        while True:
            for ci in self.clauses: # kb.clauses: # self.clauses:
                # print("\n---NEXT Ci---")
                for cj in  self.clauses: # kb.clauses: # self.clauses:
                    resolvents = MazeClause.resolve(ci,cj)
                    # print("\nRESOLVE")
                    # print(str(ci) + "\n" + str(cj))
                    # for r in resolvents:
                    #     print("->" + str(r))
                    if MazeClause([]) in resolvents: return True
                    new.update(resolvents)
            if new.issubset(self.clauses): return False
            # if new.issubset(kb.clauses): return False
            for n in new:
                self.tell(n)
                # kb.tell(n)
        # Brute Force :(
        # resolvents = set()
        # new = set()
        # resolvents.add(query)
        # while len(resolvents) > 0:
        #     for r in resolvents:
        #         self.tell(r)
        #     for ci in self.clauses:
        #         resolvents = set()
        #         for cj in self.clauses:
        #             res = MazeClause.resolve(ci,cj)
        #             if MazeClause([]) in res:
        #                 return True
        #             resolvents.update(res)
        return False

    def __str__ (self):
        s = ""
        for c in self.clauses:
            s = s + str(c) + "\n"
        return s

class MazeKnowledgeBaseTests(unittest.TestCase):
    def test_mazekb1(self):
        kb = MazeKnowledgeBase()
        kb.tell(MazeClause([(("X", (1, 1)), True)]))
        self.assertTrue(kb.ask(MazeClause([(("X", (1, 1)), True)])))

    def test_mazekb2(self):
        kb = MazeKnowledgeBase()
        kb.tell(MazeClause([(("X", (1, 1)), False)]))
        kb.tell(MazeClause([(("X", (1, 1)), True), (("Y", (1, 1)), True)]))
        self.assertTrue(kb.ask(MazeClause([(("Y", (1, 1)), True)])))

    def test_mazekb3(self):
        kb = MazeKnowledgeBase()
        kb.tell(MazeClause([(("X", (1, 1)), False), (("Y", (1, 1)), True)]))
        kb.tell(MazeClause([(("Y", (1, 1)), False), (("Z", (1, 1)), True)]))
        kb.tell(MazeClause([(("W", (1, 1)), True), (("Z", (1, 1)), False)]))
        kb.tell(MazeClause([(("X", (1, 1)), True)]))
        # self.assertTrue(kb.ask(MazeClause([(("W", (1, 1)), True)])))
        self.assertFalse(kb.ask(MazeClause([(("Y", (1, 1)), False)])))

    def test_mazekb4(self):
        kb = MazeKnowledgeBase()
        kb.tell(MazeClause([(("X", (1, 1)), False), (("Y", (1, 1)), True), (("W", (1, 1)), True)]))
        kb.tell(MazeClause([(("W", (1, 1)), False), (("Z", (1, 1)), False), (("S", (1, 1)), True)]))
        kb.tell(MazeClause([(("S", (1, 1)), False), (("T", (1, 1)), False)]))
        kb.tell(MazeClause([(("X", (1, 1)), True), (("T", (1, 1)), True)]))
        kb.tell(MazeClause([(("W", (1, 1)), True)]))
        kb.tell(MazeClause([(("T", (1, 1)), True)]))
        self.assertTrue(kb.ask(MazeClause([(("Z", (1, 1)), False)])))

    def test_mazekb5(self):
        kb = MazeKnowledgeBase()
        kb.tell(MazeClause([(("X", (1, 1)), False), (("Y", (1, 1)), True), (("W", (1, 1)), True)]))
        kb.tell(MazeClause([(("W", (1, 1)), False), (("Z", (1, 1)), False), (("S", (1, 1)), True)]))
        kb.tell(MazeClause([(("S", (1, 1)), False), (("T", (1, 1)), False)]))
        kb.tell(MazeClause([(("X", (1, 1)), True), (("T", (1, 1)), True)]))
        kb.tell(MazeClause([(("W", (1, 1)), True)]))
        kb.tell(MazeClause([(("T", (1, 1)), True)]))
        self.assertTrue(kb.ask(MazeClause([(("Z", (1, 1)), True), (("W", (1, 1)), True)])))

    def test_mazekb6(self):
        kb = MazeKnowledgeBase()
        kb.tell(MazeClause([(("X", (1, 1)), False), (("Y", (1, 1)), False), (("Z", (1, 1)), False)]))
        kb.tell(MazeClause([(("X", (1, 1)), True)]))
        self.assertFalse(kb.ask(MazeClause([(("Z", (1, 1)), False)])))
        kb.tell(MazeClause([(("Y", (1, 1)), True)]))
        self.assertTrue(kb.ask(MazeClause([(("Z", (1, 1)), False)])))


if __name__ == "__main__":
    unittest.main()
