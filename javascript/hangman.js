const wordList = ["ZYGOTE", "ACCEPT", "DOUBLE", "DOCTOR", "ASSIGN", "ZOMBIE",
                    "ZODIAC", "DECIDE", "ATTACK", "BUTTER", "BATTER", "CRUSTY",
                    "PIRATE", "ARTIST", "ACIDIC", "BASTER", "FASTER", "FATHER",
                    "MASTER", "MOTHER", "TRASHY", "TROPIC", "ZOETIC", "INGOTS",
                    "HOPING", "RANDOM", "REGION", "REPAIR", "GARDEN", "GLOBAL",
                    "GOLDEN", "GROWTH", "TARGET", "THEORY", "TRAVEL", "TRYING",
                    "POISON", "PLAYER", "PLANET", "FABRIC", "FAMOUS", "EDITOR",
                    "EASTER", "EITHER", "ERRORS", "EXCEPT", "VICTIM", "VISION",
                    "VACUUM", "VOLUME", "CAMPUS", "CAMERA", "CAUGHT", "CARBON",
                    "CHEESE", "HIJACK", "CHARGE", "NAUSEA", "NOISES", "NOTING",
                    "NUMBER", "NATURE", "IMPACT", "INVEST", "INJURY", "IGNORE",
                    "IMMUNE", "INSERT", "INTEND", "JACKAL", "JUDGED", "JAILOR",
                    "JAMMED", "JALOPY", "JARGON", "JAGUAR", "JUNGLE", "MEDIUM",
                    "METHOD", "MINUTE", "MENTAL", "MIDDLE", "MEMORY", "MODERN",
                    "SAMPLE", "SEARCH", "SEASON", "SECRET", "SECTOR", "SELECT",
                    "KNIGHT", "KIDNEY", "KARATE", "KATANA", "KABUKI", "KERNEL",
                    "KINDLE", "BEAUTY", "BEYOND", "BRIDGE"];
const defList = ["A fertilized egg", "Consent to receive", "Multiply by two",
                    "Liscenced physician", "To allocate or appoint", "Undead person",
                    "Astrological signs", "Making a choice", "To take aggresive action against",
                    "Churned dairy product", "Unbaked food goo", "Having a hard outer layer",
                    "Steals for a living", "Makes creative works", "pH < 7", "Tube with a rubber bulb on one end",
                    "To accelerate", "Male parent", "Person with control over you", "Female parent",
                    "Of poor quality", "Hot and humid", "Of or relating to life", "Bars of smelted metal",
                    "To want something to happen", "Chosen without conscious decision", "An area", "To restore",
                    "Plants are grown here", "Worldwide", "Auric", "To become larger", "Object to be aimed at",
                    "Idea used to account for a situation", "To change locations", "To put forth effort",
                    "A hazardous substance", "You", "Celestial body", "Fibers woven together", "Well-known",
                    "Checks documents for errors", "Egg holiday", "One way or the other", "Mistakes",
                    "Not including something", "Recipient of misfortune", "Seeing something in a trance",
                    "Common household appliance", "Measurement of space", "COGS is one of these", "Captures images",
                    "Interceptted", "Extremely common chemical element", "Fermented dairy product", "To commandeer",
                    "To move forward at high speed", "A feeling of sickness", "Sounds", "To acknowledge something",
                    "Numerics", "Defining traits", "To strike something", "Buy low, sell high", "Ouch",
                    "Not listening", "Unaffected", "Placing one thing into another", "Meaning to do something",
                    "The oldest canids", "GUILTY!", "Imprisoner", "Got stuck", "A poor-quality vehicle",
                    "Technical lingo", "Jungle cat", "Areas of dense foliage", "A vessel for something",
                    "Process for a task", "Tiny", "Using your mind", "In the center", "Data storage",
                    "Opposite of ancient", "A small part of", "Looking for something specific", "A quarter of the year",
                    "Hidden knowledge", "A distinct portion of", "To carefully choose", "Mideval soldier",
                    "Internal organ", "Martial artform", "a type of Japanese sword", "a form of Japanese theater",
                    "Central core or essence", "Set on fire", "Aethetically pleasing", "Out of range",
                    "To create a connection between"];

var wrongGuesses = []
var partArray = []
var wordArray = []
var gameWin = 0
var gameLose = 0

function resetGame() {
    var i;
    for (i = 0; i < wordArray.length; i++) {
        document.getElementById(("letter" + (i+1))).innerHTML = '-';
    }
    var x;
    for (x = 0; x < partArray.length; x++) {
        document.getElementById(("part" + (x+1))).style.display = "none";
		
    }
	document.getElementById("part0").style.display = "block";
    partArray = [];
    wordArray = [];
    gameWin = 0;
    gameLose = 0;
	wrongGuesses = [];
	document.getElementById("wrongGuesses").innerText = "";
}

function guessRandom() {
    let guessIndex = Math.floor(Math.random() * 100);
    guessWord = wordList[guessIndex]
    guessDef = defList[guessIndex]
	document.getElementById("demo2").innerText = guessWord;
	document.getElementById("demoHint").innerText = guessDef;
}

function fillWordArray() {
    var i;
    for (i = 0; i < 6; i++) {
        wordArray[i] = guessWord.slice(i, (i+1));
		document.getElementById(("letter" + (i+1))).innerHTML = '-';
        document.getElementById(("letter" + (i+1))).style.display = "block";
		
		
    }
	
}

function checkGuess() {
    var i;
    let guess = document.getElementById("userGuess").value.toUpperCase();
    let failCheck = 0;
    for (i = 0; i < 6; i++) {
        if (guess == wordArray[i]) {
			document.getElementById(("letter" + (i+1))).innerHTML = wordArray[i]
            gameWin++;
        }
        else {
            failCheck++
        }
    }
    if (failCheck == 6) {
        let wrongNum = wrongGuesses.length
        let partNum = partArray.length
        document.getElementById(("part" + (partNum+1))).style.display = "block";
        partArray[partNum] = ("dummyPart" + (partNum+1));
        wrongGuesses[wrongNum] = guess
        document.getElementById("wrongGuesses").innerText = wrongGuesses
        gameLose++;
		
		if (document.getElementById("part0").style.display === "block") {
			document.getElementById("part1").style.display = "block";
			document.getElementById("part0").style.display = "none";
		}
		else if (document.getElementById("part1").style.display === "block") {
			document.getElementById("part2").style.display = "block";
			document.getElementById("part1").style.display = "none";
		}
		else if (document.getElementById("part2").style.display === "block") {
			document.getElementById("part3").style.display = "block";
			document.getElementById("part2").style.display = "none";
		}
		else if (document.getElementById("part3").style.display === "block") {
			document.getElementById("part4").style.display = "block";
			document.getElementById("part3").style.display = "none";
		}
		else if (document.getElementById("part4").style.display === "block") {
			document.getElementById("part5").style.display = "block";
			document.getElementById("part4").style.display = "none";
		}
		else if (document.getElementById("part5").style.display === "block") {
			document.getElementById("part6").style.display = "block";
			document.getElementById("part5").style.display = "none";
		}
			
    }
}

function gameOver() {
    setTimeout(function (){
        if (gameWin >= 6) {
            window.alert("You won!");
            resetGame();
        } else if (gameLose == 6) {
            window.alert("You lose!");
            resetGame()
        } else {}
    }, 2000);
}