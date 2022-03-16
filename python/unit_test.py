# unit_test.py

from Enemies import enemies
from Shooter import shooter
from Bullets import bullets

passCount = 0

print(" ")
print("====================================")
print(" ")

# Unit test 1, bullet collision vs player

# Test 1-A: bullet far from player

testPlayer = shooter(50, 50, 1, "#ffffff") # player object
testBullet = bullets(200, 200, 1, "#ffffff") # bullet object

expectedResult = False
actualResult = testPlayer.playerCollide(testBullet)

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 1-A result is PASS")
else:
    print("Unit Test 1-A result is FAIL")

# Test 1-B: bullet close on one axis, far on the other

testPlayer = shooter(50, 50, 1, "#ffffff") # player object
testBullet = bullets(55, 200, 1, "#ffffff") # bullet object

expectedResult = False
actualResult = testPlayer.playerCollide(testBullet)

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 1-B result is PASS")
else:
    print("Unit Test 1-B result is FAIL")

# Test 1-C: bullet close to player

testPlayer = shooter(50, 50, 1, "#ffffff") # player object
testBullet = bullets(55, 40, 1, "#ffffff") # bullet object

expectedResult = True
actualResult = testPlayer.playerCollide(testBullet)

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 1-C result is PASS")
else:
    print("Unit Test 1-C result is FAIL")

# Test 1-D: bullet is just outside collision range

testPlayer = shooter(50, 50, 1, "#ffffff") # player object
testBullet = bullets(70, 30, 1, "#ffffff") # bullet object

expectedResult = False
actualResult = testPlayer.playerCollide(testBullet)

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 1-D result is PASS")
else:
    print("Unit Test 1-D result is FAIL")

print(" ")
print("====================================")
print(" ")

# Unit test 2, bullet collision vs enemy

# Test 2-A: bullet far from enemy

testEnemy = enemies(50, 50, 1, "#ffffff", False) # enemy object
testBullet = bullets(200, 200, 1, "#ffffff") # bullet object

expectedResult = False
actualResult = testEnemy.enemyCollide(testBullet)

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 2-A result is PASS")
else:
    print("Unit Test 2-A result is FAIL")

# Test 2-B: bullet close on one axis, far on the other

testEnemt = enemies(50, 50, 1, "#ffffff", False) # enemy object
testBullet = bullets(55, 200, 1, "#ffffff") # bullet object

expectedResult = False
actualResult = testEnemy.enemyCollide(testBullet)

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 2-B result is PASS")
else:
    print("Unit Test 2-B result is FAIL")

# Test 2-C: bullet close to enemy

testEnemy = enemies(50, 50, 1, "#ffffff", False) # enemy object
testBullet = bullets(55, 40, 1, "#ffffff") # bullet object

expectedResult = True
actualResult = testEnemy.enemyCollide(testBullet)

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 2-C result is PASS")
else:
    print("Unit Test 2-C result is FAIL")

# Test 2-D: bullet is just outside collision range

testEnemy = enemies(50, 50, 1, "#ffffff", False) # enemy object
testBullet = bullets(65, 35, 1, "#ffffff") # bullet object

expectedResult = False
actualResult = testEnemy.enemyCollide(testBullet)

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 2-D result is PASS")
else:
    print("Unit Test 2-D result is FAIL")

print(" ")
print("====================================")
print(" ")

# Unit Test 3, detecting if bullet has zero hp or has gone offscreen so it can be flagged for removal
# Canvas size for this test is assumed to be 600 width and 500 height

# Test 3-A: bullet hp > 0, not offscreen

testBullet = bullets(100, 200, 1, "#ffffff") # bullet object

expectedResult = False
actualResult = testBullet.bulletCheck()

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 3-A result is PASS")
else:
    print("Unit Test 3-A result is FAIL")

# Test 3-B: bullet hp > 0, bullet is below canvas boundary

testBullet = bullets(100, 501, 1, "#ffffff") # bullet object

expectedResult = True
actualResult = testBullet.bulletCheck()

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 3-B result is PASS")
else:
    print("Unit Test 3-B result is FAIL")

# Test 3-C: bullet hp > 0, bullet is above canvas boundary

testBullet = bullets(100, -1, 1, "#ffffff") # bullet object

expectedResult = True
actualResult = testBullet.bulletCheck()

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 3-C result is PASS")
else:
    print("Unit Test 3-C result is FAIL")

# Test 3-D: bullet hp is 0, but not offscreen

testBullet = bullets(100, 200, 0, "#ffffff") # bullet object

expectedResult = True
actualResult = testBullet.bulletCheck()

if expectedResult == actualResult:
    passCount += 1
    print("Unit Test 3-D result is PASS")
else:
    print("Unit Test 3-D result is FAIL")

print(" ")
print("====================================")
print(" ")

if passCount == 12:
    print("ALL UNIT TESTS HAVE PASSED")
else:
    print("AT LEAST ONE TEST DID NOT PASS")