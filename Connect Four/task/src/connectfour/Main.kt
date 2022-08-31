package connectfour

//import java.util.zip.Checksum

fun boardPrint(rows: Int, columns: Int, board: MutableList<MutableList<Int>>) {
    for (i in 1..columns) {
        print(" $i")
    }
    for (i in 0 until rows) {
        println()
        for (j in 0 until columns) {
            print("║")
            when (board[rows - 1 - i][j]) {
                1 -> {
                    print("o")
                }

                2 -> {
                    print("*")
                }

                else -> {
                    print(" ")
                }
            }
            if (j == columns - 1) {
                print("║")
            }
        }
    }
    println()
    print("╚═")
    for (i in 1 until columns) {
        print("╩═")
    }
    println("╝")
}

fun checkWiningCondition(board: MutableList<MutableList<Int>>, turn: Int, lastPlacedRow: Int, lastPlacedCol: Int): Boolean {
    //horizontal check
    for (col in 0 until board.first().size - 3) {
        if (board[lastPlacedRow][col] == turn &&
            board[lastPlacedRow][col + 1] == turn &&
            board[lastPlacedRow][col + 2] == turn &&
            board[lastPlacedRow][col + 3] == turn
        ) {
            return true
        }
    }
    //vertical check
    for (row in board.lastIndex - 3 downTo 0) {
        if (board[row][lastPlacedCol] == turn &&
            board[row + 1][lastPlacedCol] == turn &&
            board[row + 2][lastPlacedCol] == turn &&
            board[row + 3][lastPlacedCol] == turn
        ) {
            return true
        }
    }
    //downward diagonal check
    for (row in 3..board.lastIndex) {
        for (col in 0 until board.first().size - 3) {
            if (board[row][col] == turn &&
                board[row - 1][col + 1] == turn &&
                board[row - 2][col + 2] == turn &&
                board[row - 3][col + 3] == turn
            ) {
                return true
            }
        }
    }
    //check upward diagonal
    for (row in 0 until board.size - 3) {
        for (col in 0 until board.first().size - 3) {
            if (board[row][col] == turn &&
                board[row + 1][col + 1] == turn &&
                board[row + 2][col + 2] == turn &&
                board[row + 3][col + 3] == turn
            ) {
                return true
            }
        }
    }
    return false
}

class Player(val name: String, var score: Int = 0)

fun main() {
    //game input
    println("Connect Four")
    println("First player's name:")
    val firstPlayer = Player(readln())
    println("Second player's name:")
    val secondPlayer = Player(readln())
    var rows = 6
    var columns = 7
    do {
        println("Set the board dimensions (Rows x Columns)\nPress Enter for default (6 x 7)")
        var checkValidInput = false
        val dimensions = readln().replace("\\s".toRegex(), "")
        if (dimensions == "") {
            checkValidInput = true
        } else if ("\\d\\d?[xX]\\d\\d?".toRegex().matches(dimensions)) {
            rows = dimensions.first().digitToInt()
            columns = dimensions.last().digitToInt()
            if (rows !in 5..9) {
                println("Board rows should be from 5 to 9")
            } else if (columns !in 5..9) {
                println("Board columns should be from 5 to 9")
            } else {
                checkValidInput = true
            }
        } else {
            println("Invalid input")
        }
    } while (!checkValidInput)

    var numberOfGames = 0
    do {
        println(
            "Do you want to play single or multiple games?\n" +
                    "For a single game, input 1 or press Enter\n" +
                    "Input a number of games:"
        )
        when (val inputNumberOfGames = readln()) {
            "" -> numberOfGames = 1
            in "1".."9" -> numberOfGames = inputNumberOfGames.toInt()
            else -> println("Invalid input")
        }
    } while (numberOfGames !in 1..9)

    println("${firstPlayer.name} VS ${secondPlayer.name}\n$rows X $columns board")
    if (numberOfGames == 1) {
        println("Single game")
    } else {
        println("Total $numberOfGames games")
    }

    //game logic
    for (i in 1..numberOfGames) {
        if (numberOfGames != 1) {
            println("Game #$i")
        }
        val board = MutableList(rows) { MutableList(columns) { 0 } }
        boardPrint(rows, columns, board)
        var turn: Int = if (i % 2 == 1) {
            1
        } else {
            2
        }
        var inputInt: Int
        val rowsBusy = MutableList(columns) { 0 }
        loop@ do {
            if (turn == 1) {
                println("${firstPlayer.name}'s turn:")
            } else {
                println("${secondPlayer.name}'s turn:")
            }
            val input: String = readln()
            if (input == "end") {
                break
            } else if (input.any { it !in '0'..'9' } || input == "") {
                println("Incorrect column number")
            } else {
                inputInt = input.toInt()
                when (inputInt) {
                    in 1..columns -> if (rowsBusy[inputInt - 1] < rows) {
                        board[rowsBusy[inputInt - 1]][inputInt - 1] = turn
                        rowsBusy[inputInt - 1] += 1
                        boardPrint(rows, columns, board)
                        if (checkWiningCondition(board, turn, rowsBusy[inputInt - 1] - 1, inputInt - 1)) {
                            if (turn == 1) {
                                println("Player ${firstPlayer.name} won")
                                firstPlayer.score += 2
                            } else if (turn == 2) {
                                println("Player ${secondPlayer.name} won")
                                secondPlayer.score += 2
                            }
                            if (numberOfGames != 1) {
                                println("Score\n${firstPlayer.name}: ${firstPlayer.score} ${secondPlayer.name}: ${secondPlayer.score}")
                            }
                            break@loop
                        }
                        if (turn == 1) {
                            turn += 1
                        } else turn -= 1
                    } else {
                        println("Column $inputInt is full")
                    }

                    else -> println("The column number is out of range (1 - $columns)")
                }
                if (rowsBusy.all { it == rows }) {
                    println("It is a draw")
                    firstPlayer.score += 1
                    secondPlayer.score += 1
                    println("Score\n${firstPlayer.name}: ${firstPlayer.score} ${secondPlayer.name}: ${secondPlayer.score}")
                    break
                }

            }
        } while (true)
    }
    println("Game over!")
}