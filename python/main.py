# main.py

import tkinter as tk
import time
import random
import tkinter.font as tkFont
from Shooter import shooter
from Bullets import bullets
from Enemies import enemies
from Text import text

# Font object for start screen text objects
# startFont = tkFont.Font(family='Arial Black', size=20)


# Empty lists for game objects
enemyBullets = []
playerBullets = []
enemyList = []

# Setting global variables with a class
class gameState:
    gameOver = False
    startScreen = True
    showScore = True
    playerScore = 0
    enemiesDefeated = 0

# Sets up the canvas for the game
master = tk.Tk()
master.title("Space Shooters")
fonts = tk.Text(master)
startFont = tkFont.Font(family='Arial Black', size=20)
w = tk.Canvas(master, width=600, height=500, bg="#000000")
fonts.configure(font=startFont)
w.pack()

# text objects for the start screen
startText1 = text(300, 100, 1, "#ffffff", "SPACE SHOOTERS", startFont)
startText2 = text(300, 150, 1, "#ffffff", "Use the arrow keys to move!", startFont)
startText3 = text(300, 200, 1, "#ffffff", "Use the spacebar to shoot lasers!", startFont)
startText4 = text(300, 250, 1, "#ffffff", "Avoid enemy lasers! If you're hit", startFont)
startText5 = text(300, 300, 1, "#ffffff", "three times then you lose!", startFont)
startText6 = text(300, 400, 1, "#ffffff", "Press Enter to start the game!", startFont)

textList = [startText1, startText2, startText3, startText4, startText5, startText6]

# Creates player-controlled shooter object
player = shooter(250, 400, 5, "#0000ff")

# Randomly generates 5 enemy objects
for i in range(1, 6):
    enemy = enemies(random.randrange(50, 550), random.randrange(25, 100), 3, "#ff0000", False)
    enemyList.append(enemy)

def leftPressed(event):
    '''Checks for left input and moves shooter left unless they are too far left already.'''
    if player.x >= 30:
        player.moveLeft()

def rightPressed(event):
    '''Checks for right input and moves shooter right unless they are too far right already.'''
    if player.x <= 570:
        player.moveRight()

def upPressed(event):
    '''Checks for up input and moves shooter up unless they are too far up already.'''
    if player.y >= 300:
        player.moveUp()

def downPressed(event):
    '''Checks for down input and moves shooter down unless they are too far down already.'''
    if player.y <= 470:
        player.moveDown()

def spacePressed(event):
    '''Checks for spacebar input and fires a bullet from the shooter object.'''
    bullet = bullets(player.x + 10, player.y - 25, 1, "#ffffff")
    playerBullets.append(bullet)

def startGame(event):
    '''Starts main game loop once player is ready.'''
    gameState.startScreen = False

def endGame(event):
    '''Ends game after final score is shown.'''
    gameState.showScore = False

# Keybinds for player controls
master.bind('<Left>', leftPressed)
master.bind('<Right>', rightPressed)
master.bind('<Up>', upPressed)
master.bind('<Down>', downPressed)
master.bind('<space>', spacePressed)
master.bind('<Return>', startGame)
master.bind('<Escape>', endGame)

# Start screen loop
while gameState.startScreen == True:
    w.delete("all")
    for text in textList:
        text.drawText(w)
    w.update()

# Main while loop
while gameState.gameOver == False:
    w.delete("all")

    # For each bullet the player fires this loop has it move up until it hits an enemy or goes off screen.
    for b in playerBullets:
        b.drawBullets(w)
        if b.bulletCheck():
            playerBullets.remove(b)
        else:
            b.player_moveBullet()

    # For each bullet an enemy fires this loop has it move down until it hits the player or goes off screen.
    for b in enemyBullets:
        b.drawBullets(w)
        if b.bulletCheck():
            enemyBullets.remove(b)
        else:
            b.enemy_moveBullet()

    # This loop handles player bullet vs enemy collision
    for b in playerBullets:
        for e in enemyList:
            if e.enemyCollide(b):
                b.hp -= 1
                e.hitEnemies()
    # This loop handles enemy bullet vs player collision
    for b in enemyBullets:
        if player.playerCollide(b):
            b.hp -= 1
            player.hitPlayer()

    # This loop handles enemy movement and checks the enemy shoot flag to see if they should fire a bullet
    for e in enemyList:
        if e.hp > 0:
            e.drawEnemies(w)
            e.moveEnemies()
            if e.shoot == True:
                fire = bullets(e.x + 8, e.y + 18, 1, "#ffff00")
                enemyBullets.append(fire)
        # This statement removes enemies that drop to zero hp and then increments the player's kill count and score
        elif e.hp <= 0:
            enemyList.remove(e)
            gameState.playerScore += 500
            gameState.enemiesDefeated += 1

    # Conditions for Game Over flag
    if player.hp <= 0 or gameState.enemiesDefeated >= 5:
        gameState.gameOver = True

    player.drawShooter(w)
    w.update()
    time.sleep(0.05)

from Text import text
scoreText = (str(gameState.playerScore) + " POINTS!")
finalScore1 = text(300, 150, 1, "#ffffff", "GAME OVER!", startFont)
finalScore2 = text(300, 200, 1, "#ffffff", "YOUR SCORE WAS:", startFont)
finalScore3 = text(300, 300, 1, "#ffffff", scoreText, startFont)
finalScore4 = text(300, 400, 1, "#ffffff", "Press Esc to close the game!", startFont)
scoreList = [finalScore1, finalScore2, finalScore3, finalScore4]

# Prints the player's final score after the game ends
while gameState.showScore == True:
    w.delete("all")
    for text in scoreList:
        text.drawText(w)
    w.update()