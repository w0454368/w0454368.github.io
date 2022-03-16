# Text.py

import tkinter
from BaseShape import baseShape

class text(baseShape):
    '''Subclass for Text methods.'''
    def __init__(self, x, y, hp, colour, text, font):
        baseShape.__init__(self, x, y, hp, colour)
        self.text = text
        self.font = font

    def drawText(self, w):
        '''Method to draw text objects'''
        w.create_text(self.x, self.y, text=self.text, font=self.font,
                      fill=self.colour)