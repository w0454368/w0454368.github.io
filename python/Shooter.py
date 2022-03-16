# Shooter.py

import tkinter
import time
from BaseShape import baseShape

class shooter(baseShape):
    '''Subclass for Shooter methods.'''
    def drawShooter(self, w):
        '''Method to draw the shooter that the player controls.'''
        points = [self.x, self.y, self.x + 20, self.y, self.x + 17, self.y - 15, self.x + 15, self.y - 6,
                  self.x + 10, self.y - 20, self.x + 5, self.y - 6, self.x + 3, self.y - 15]
        w.create_polygon(points, outline="#ffffff", fill=self.colour, width=1)

    def playerCollide(self, other):
        '''Method for collision detection against the player.'''
        if (abs(other.x - self.x) < 20) and (abs(other.y - self.y) < 20):
            return True
        else:
            return False

    def moveLeft(self):
        '''Moves the shooter to the left.'''
        self.x -= 5

    def moveRight(self):
        '''Moves the shooter to the right.'''
        self.x += 5

    def moveUp(self):
        '''Moves the shooter up.'''
        self.y -= 5

    def moveDown(self):
        '''Moves the shooter down.'''
        self.y += 5

    def hitPlayer(self):
        '''Decrements health when the player is hit.'''
        self.hp -= 1