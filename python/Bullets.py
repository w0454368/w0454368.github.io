# Bullets.py

import tkinter
import time
from BaseShape import baseShape

class bullets(baseShape):
    '''Subclass for bullets methods.'''
    def drawBullets(self, w):
        '''Method for drawing bullet projectiles.'''
        w.create_line(self.x, self.y, self.x, self.y - 5, fill=self.colour, width=3)

    def player_moveBullet(self):
        '''Handles movement for the player's bullets.'''
        self.y -= 10

    def enemy_moveBullet(self):
        '''Handles movement for enemy bullets.'''
        self.y += 10

    def bulletCheck(self):
        '''Detects if bullet has hit a ship or gone offscreen.'''
        if self.hp <= 0 or self.y < 0 or self.y > 500:
            return True
        else:
            return False