import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static javax.swing.JOptionPane.NO_OPTION;
import static javax.swing.JOptionPane.showMessageDialog;



public class OOPTicTacToe {

    private static final int ROW = 3;
    private static final int COL= 3;
    private static final int MOVES_FOR_WIN = 5;
    private static final int MOVES_FOR_TIE = 7;
    private static int GlobalMoveTracker=0;
    private static TicTacToeTile Tile1 = new TicTacToeTile(1,1," ");
    private static TicTacToeTile Tile2 = new TicTacToeTile(1,2," ");
    private static TicTacToeTile Tile3 = new TicTacToeTile(1,3," ");
    private static TicTacToeTile Tile4 = new TicTacToeTile(2,1," ");
    private static TicTacToeTile Tile5 = new TicTacToeTile(2,2," ");
    private static   TicTacToeTile Tile6 = new TicTacToeTile(2,3," ");
    private static TicTacToeTile Tile7 = new TicTacToeTile(3,1," ");
    private static TicTacToeTile Tile8 = new TicTacToeTile(3,2," ");
    private static   TicTacToeTile Tile9 = new TicTacToeTile(3,3," ");
    private static TicTacToeWindow GameWindow = new TicTacToeWindow("Tic-Tac-Toe");
    private static  JPanel GameBoard = new JPanel(new GridLayout(3,3));
    private static TicTacToeTile[][] TieManager ={ {Tile1, Tile2, Tile3}, {Tile4, Tile5, Tile6}, {Tile7, Tile8, Tile9}};


    private static ActionListener BadMove = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            showMessageDialog(GameBoard,"Invalid move!");

        }
    };
    public static void main(String[] args) {
        PlayerManager CurrentPlayer = new PlayerManager("X");
        GameManager ShouldPlayGame = new GameManager(true);
        GameManager GameInProgress = new GameManager(true);
        TurnManager GotAMove= new TurnManager(false);
        GameWindow.add(GameBoard);
        GameBoard.add(Tile1);
        GameBoard.add(Tile2);
        GameBoard.add(Tile3);
        GameBoard.add(Tile4);
        GameBoard.add(Tile5);
        GameBoard.add(Tile6);
        GameBoard.add(Tile7);
        GameBoard.add(Tile8);
        GameBoard.add(Tile9);
        GameWindow.show();
            do{
                CurrentPlayer.setPlayer("X");
                ShouldPlayGame.setContinueStatus(true);
                GlobalMoveTracker=0;
                BoardReset();
                do  //game loop
                    {
                        //get move
                    do {

                        ActionListener TileListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent TileClicked) {
                            TicTacToeTile SourceTile = (TicTacToeTile) TileClicked.getSource();
                            if (SourceTile.getPlayer().equals(" "))
                            {
                                //board[SourceTile.getRow()-1][SourceTile.getCol()-1]=GlobalPlayer.getPlayer();
                                SourceTile.setPlayer(CurrentPlayer.getPlayer());
                                SourceTile.setText(CurrentPlayer.getPlayer());
                                SourceTile.addActionListener(BadMove);
                                GotAMove.setCompleted(true);

                            }



                        }
                    };
                        Tile1.addActionListener(TileListener);
                        Tile2.addActionListener(TileListener);
                        Tile3.addActionListener(TileListener);
                        Tile4.addActionListener(TileListener);
                        Tile5.addActionListener(TileListener);
                        Tile6.addActionListener(TileListener);
                        Tile7.addActionListener(TileListener);
                        Tile8.addActionListener(TileListener);
                        Tile9.addActionListener(TileListener);
                        ;
                    } while (!GotAMove.isCompleted());
                    GlobalMoveTracker=GlobalMoveTracker+1;



                    if (isWin(CurrentPlayer.getPlayer())){showMessageDialog(GameBoard, "Player "+CurrentPlayer.getPlayer()+" Wins!"); GameInProgress.setContinueStatus(false);}

                       if (GlobalMoveTracker >= MOVES_FOR_TIE){
                        if (isTie() || GlobalMoveTracker==9)
                        {
                            showMessageDialog(GameBoard,"It's a tie!");
                            GameInProgress.setContinueStatus(false);
                        }}
                        if (CurrentPlayer.getPlayer().equals("X")){CurrentPlayer.setPlayer("O");}
                        else {CurrentPlayer.setPlayer("X");}
                        GotAMove.setCompleted(false);

                }while(GameInProgress.getContinueStatus());
                int FinishedStatus = JOptionPane.showConfirmDialog(GameBoard,"Keep playing?","Continue", JOptionPane.YES_NO_OPTION);
                if (FinishedStatus == NO_OPTION){ShouldPlayGame.setContinueStatus(false);}
            }while (ShouldPlayGame.getContinueStatus());


    }
    private static void BoardReset()
    {
        Tile1.setPlayer(" ");
        Tile2.setPlayer(" ");
        Tile3.setPlayer(" ");
        Tile4.setPlayer(" ");
        Tile5.setPlayer(" ");
        Tile6.setPlayer(" ");
        Tile7.setPlayer(" ");
        Tile8.setPlayer(" ");
        Tile9.setPlayer(" ");

        Tile1.setText(" ");
        Tile2.setText(" ");
        Tile3.setText(" ");
        Tile4.setText(" ");
        Tile5.setText(" ");
        Tile6.setText(" ");
        Tile7.setText(" ");
        Tile8.setText(" ");
        Tile9.setText(" ");

        Tile1.removeActionListener(BadMove);
        Tile2.removeActionListener(BadMove);
        Tile3.removeActionListener(BadMove);
        Tile4.removeActionListener(BadMove);
        Tile5.removeActionListener(BadMove);
        Tile6.removeActionListener(BadMove);
        Tile7.removeActionListener(BadMove);
        Tile8.removeActionListener(BadMove);
        Tile9.removeActionListener(BadMove);

        GlobalMoveTracker=0;
    }

    private static boolean isWin(String player)
    {
        if (isRowWin(player) || isColWin(player) || isDiagWin(player))
        {return true;}
        {return false;}
    }

    private static boolean isRowWin(String player)
    {
        if(Tile1.getPlayer().equals(player) && Tile2.getPlayer().equals(player) && Tile3.getPlayer().equals(player)){return true;}
        else if (Tile4.getPlayer().equals(player) && Tile5.getPlayer().equals(player) && Tile6.getPlayer().equals(player)) {return true;}
        else if (Tile7.getPlayer().equals(player) && Tile8.getPlayer().equals(player) && Tile9.getPlayer().equals(player)) {return true;}
        else return false;
    }

    private static boolean isColWin(String player)
    {if(Tile1.getPlayer().equals(player) && Tile4.getPlayer().equals(player) && Tile7.getPlayer().equals(player)){return true;}
     else if (Tile2.getPlayer().equals(player) && Tile5.getPlayer().equals(player) && Tile8.getPlayer().equals(player)){return true;}
     else if (Tile3.getPlayer().equals(player) && Tile6.getPlayer().equals(player) && Tile9.getPlayer().equals(player)){return true;}
     else return false;
    }


    private static boolean isDiagWin(String player){
        if(Tile1.getPlayer().equals(player) && Tile5.getPlayer().equals(player) && Tile9.getPlayer().equals(player)){return true;}
        else if (Tile3.getPlayer().equals(player) && Tile5.getPlayer().equals(player) && Tile7.getPlayer().equals(player)){return true;}
        else return false;
    }

    private static boolean isTie() {
        boolean xFlag = false;
        boolean oFlag = false;
        //ROW TEST
        for (int row = 0; row < ROW; row++) {
            if (
                    TieManager[row][0].getPlayer().equals("X") ||
                            TieManager[row][1].getPlayer().equals("X") ||
                            TieManager[row][2].getPlayer().equals("X")) {
                xFlag = true;
            }

            if (
                    TieManager[row][0].getPlayer().equals("O") ||
                            TieManager[row][1].getPlayer().equals("O") ||
                            TieManager[row][2].getPlayer().equals("O")
            ) {
                oFlag = true;
            }
            if (!(xFlag && oFlag)) {
                return false;
            }
        }
        xFlag = oFlag = false;
        //COL TEST
        for (int col = 0; col < COL; col++) {
            if (
                    TieManager[0][col].getPlayer().equals("X") ||
                            TieManager[1][col].getPlayer().equals("X") ||
                            TieManager[2][col].getPlayer().equals("X")
            ) {
                xFlag = true;
            }

            if (
                    TieManager[0][col].getPlayer().equals("O") ||
                            TieManager[1][col].getPlayer().equals("O") ||
                            TieManager[2][col].getPlayer().equals("O")
            ) {
                oFlag = true;
            }

            if (!(xFlag && oFlag)) {
                return false;
            }

        }
        xFlag = oFlag = false;
        //DIAG TEST
        if (
                TieManager[0][0].getPlayer().equals("X") ||
                        TieManager[1][1].getPlayer().equals("X") ||
                        TieManager[2][2].getPlayer().equals("X")
        ) {
            xFlag = true;
        }
        if (
                TieManager[0][0].getPlayer().equals("O") ||
                        TieManager[1][1].getPlayer().equals("O") ||
                        TieManager[2][2].getPlayer().equals("O")

        ) {
            oFlag = true;
        }
        if (!(xFlag && oFlag)) {
            return false;
        }
        xFlag = oFlag = false;

        if (TieManager[0][2].getPlayer().equals("X") ||
                TieManager[1][1].getPlayer().equals("X") ||
                TieManager[2][0].getPlayer().equals("X")
        ) {
            xFlag = true;
        }

        if (TieManager[0][2].getPlayer().equals("O") ||
                TieManager[1][1].getPlayer().equals("O") ||
                TieManager[2][0].getPlayer().equals("O")
        ) {
            oFlag = true;
        }
        if (!(xFlag && oFlag)) {
            return false;
        }

        return true;


    }



    }
