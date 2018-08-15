public class ConnectFour {

    public static enum Direction {
        VERTICAL,
        HORIZONTAL,
        RIGHTDIAGONAL,
        LEFTDIAGONAL
    }

    public static class Counter {
        public int count;

        public Counter() {
            count = 0;
        }
    }

    public static class Chip {
        public boolean team;
        int row;
        int col;
        public Counter vertical;
        public Counter horizontal;
        public Counter rightDiagonal;
        public Counter leftDiagonal;

        public Chip(boolean team, int row, int col) {
            this.team = team;
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return (team) ? "1" : "2";
        }
    }

    public static class Game {
        private Chip[][] board;
        boolean turn;
        boolean gameCompleted;

        public Game() {
            // Hardcode board size for simplicity
            board = new Chip[8][8];
            turn = true;
            gameCompleted = false;
        }

        public void makeMove(int row, int col) {
            if (board[row][col] != null) {
                System.out.println("Position already filled. Try again");
                return;
            } else if (gameCompleted) {
                System.out.println("Game already completed. Start a new game");
                return;
            }

            Chip current = new Chip(turn, row, col);

            Chip top = checkBounds(row - 1, col) ? board[row - 1][col] : null;
            Chip bottom = checkBounds(row + 1, col) ? board[row - 1][col] : null;
            updateCounters(top, bottom, current, Direction.VERTICAL);

            Chip left = checkBounds(row, col-1) ? board[row - 1][col] : null;
            Chip right = checkBounds(row, col+1) ? board[row - 1][col] : null;
            updateCounters(left, right, current, Direction.HORIZONTAL);

            Chip topRight = checkBounds(row - 1, col+1) ? board[row - 1][col] : null;
            Chip bottomLeft = checkBounds(row + 1, col-1) ? board[row - 1][col] : null;
            updateCounters(topRight, bottomLeft, current, Direction.RIGHTDIAGONAL);

            Chip topLeft = checkBounds(row - 1, col-1) ? board[row - 1][col] : null;
            Chip bottomRight = checkBounds(row + 1, col+1) ? board[row - 1][col] : null;
            updateCounters(topLeft, bottomRight, current, Direction.LEFTDIAGONAL);

            printBoard();

            if (gameCompleted) {
                System.out.println("Congratulations! You win!");
                return;
            }
            // Flip for next player
            turn = !turn;
        }

        private boolean checkBounds(int row, int col) {
            return !(row < 0 || row >= board.length || col < 0 || col > board[0].length);
        }

        private void updateCounters(Chip old1, Chip old2, Chip new1, Direction dir) {
            Counter counter1 = null;
            Counter counter2 = null;
            switch(dir) {
                case VERTICAL:
                    counter1 = (old1 != null) ? old1.vertical : null;
                    counter2 = (old2 != null) ? old2.vertical : null;
                    break;
                case HORIZONTAL:
                    counter1 = (old1 != null) ? old1.horizontal : null;
                    counter2 = (old2 != null) ? old2.horizontal : null;
                    break;
                case RIGHTDIAGONAL:
                    counter1 = (old1 != null) ? old1.rightDiagonal : null;
                    counter2 = (old2 != null) ? old2.rightDiagonal : null;
                    break;
                case LEFTDIAGONAL:
                    counter1 = (old1 != null) ? old1.leftDiagonal : null;
                    counter2 = (old2 != null) ? old2.leftDiagonal : null;
                    break;
            }

            Counter simpleAdd = null;
            if (counter1 == null && counter2 == null) {
                simpleAdd = new Counter();
            } else if (counter1 == null) {
                simpleAdd = counter1;
            } else if (counter2 == null) {
                simpleAdd = counter2;
            }

            if (simpleAdd != null) {
                simpleAdd.count++;
            } else {
                simpleAdd = new Counter();
                simpleAdd.count = 1 + counter1.count + counter2.count;
                updateOldCounters(old1, old2, simpleAdd, dir);
            }

            // Now put the counter in the right field
            switch(dir) {
                case VERTICAL:
                    new1.vertical = simpleAdd;
                    break;
                case HORIZONTAL:
                    new1.horizontal = simpleAdd;
                    break;
                case RIGHTDIAGONAL:
                    new1.rightDiagonal= simpleAdd;
                    break;
                case LEFTDIAGONAL:
                    new1.leftDiagonal = simpleAdd;
                    break;
            }

            if (simpleAdd.count >= 4) {
                gameCompleted = true;
            }
        }

        private void updateOldCounters(Chip old1, Chip old2, Counter newCount, Direction dir) {
            switch(dir) {
                case VERTICAL:
                    updateUp(old1, newCount);
                    updateDown(old2, newCount);
                    break;
                case HORIZONTAL:
                    updateLeft(old1, newCount);
                    updateRight(old2, newCount);
                    break;
                case RIGHTDIAGONAL:
                    updateTopRight(old1, newCount);
                    updateBottomLeft(old2, newCount);
                    break;
                case LEFTDIAGONAL:
                    updateTopLeft(old1, newCount);
                    updateBottomRight(old2, newCount);
                    break;
            }
        }

        // Probably a nice way to do this with lambda expressions. May come back and refactor later...
        private void updateUp(Chip old, Counter newCount) {
            while (old != null) {
                old.vertical = newCount;
                old = checkBounds(old.row-1, old.col) ? board[old.row-1][old.col] : null;
            }
        }
        private void updateDown(Chip old, Counter newCount) {
            while (old != null) {
                old.vertical = newCount;
                old = checkBounds(old.row+1, old.col) ? board[old.row+1][old.col] : null;
            }
        }
        private void updateLeft(Chip old, Counter newCount) {
            while (old != null) {
                old.vertical = newCount;
                old = checkBounds(old.row, old.col-1) ? board[old.row][old.col-1] : null;
            }
        }
        private void updateRight(Chip old, Counter newCount) {
            while (old != null) {
                old.vertical = newCount;
                old = checkBounds(old.row, old.col+1) ? board[old.row][old.col+1] : null;
            }
        }
        private void updateTopRight(Chip old, Counter newCount) {
            while (old != null) {
                old.vertical = newCount;
                old = checkBounds(old.row-1, old.col+1) ? board[old.row-1][old.col+1] : null;
            }
        }
        private void updateBottomLeft(Chip old, Counter newCount) {
            while (old != null) {
                old.vertical = newCount;
                old = checkBounds(old.row+1, old.col-1) ? board[old.row+1][old.col-1] : null;
            }
        }
        private void updateTopLeft(Chip old, Counter newCount) {
            while (old != null) {
                old.vertical = newCount;
                old = checkBounds(old.row-1, old.col-1) ? board[old.row-1][old.col-1] : null;
            }
        }
        private void updateBottomRight(Chip old, Counter newCount) {
            while (old != null) {
                old.vertical = newCount;
                old = checkBounds(old.row+1, old.col+1) ? board[old.row+1][old.col+1] : null;
            }
        }

        private void printBoard() {
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length; col++) {
                    System.out.print(board[row][col]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {

        Game game = new Game();
        game.makeMove(1,1);
        game.makeMove(1,2);
    }
}
