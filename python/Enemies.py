# Enemies.py

import tkinter
import time
import random
from BaseShape import baseShape

class enemies(baseShape):
    '''Subclass for bullets methods.'''
    def __init__(self, x, y, hp, colour, shoot):
        baseShape.__init__(self, x, y, hp, colour)
        self.shoot = False

    def drawEnemies(self, w):
        '''Method for drawing bullet projectiles.'''
        w.create_polygon(self.x, self.y, self.x + 8, self.y + 15, self.x + 16, self.y,
                         outline="#ffffff", fill=self.colour, width=1)

    def enemyCollide(self, other):
        '''Method for collision detection against an enemy.'''
        if (abs(other.x - self.x) < 15) and (abs(other.y - self.y) < 15):
            return True
        else:
            return False

    def hitEnemies(self):
        '''Method for handling damage to enemies.'''
        self.hp -= 1

    def moveEnemies(self):
        '''Handles random enemy movement and setting flags for random enemy bullet firing.'''
        randMove = random.randint(1, 200)
        if randMove <= 35:
            self.shoot = False
            if self.x >= 30:
                self.x -= 5
                randMove = random.randint(1, 200)
            else:
                randMove = random.randint(1, 200)
        if randMove > 35 and randMove <= 75:
            self.shoot = False
            if self.x <= 570:
                self.x += 5
                randMove = random.randint(1, 200)
            else:
                randMove = random.randint(1, 200)
        if randMove > 75 and randMove <= 115:
            self.shoot = False
            if self.y >= 30:
                self.y -= 5
                randMove = random.randint(1, 200)
            else:
                randMove = random.randint(1, 200)
        if randMove > 115 and randMove <= 155:
            self.shoot = False
            if self.y <=270:
                self.y += 5
                randMove = random.randint(1, 200)
            else:
                randMove = random.randint(1, 200)
        if randMove > 155 and randMove <= 198:
            self.shoot = False
            randMove = random.randint(1, 200)
        if randMove > 198:
            self.shoot = True
            randMove = random.randint(1, 200)