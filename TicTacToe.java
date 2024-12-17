import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class TicTacToe {
    int boardwidth=600;
    int boardheight=650;

    JFrame frame=new JFrame("ONEK KOSHTO HOISE");
    JLabel textLabel = new JLabel();
    JPanel textPanel=new JPanel();
    JPanel boardPanel=new JPanel();

    JButton[][] board=new JButton[3][3];
    String playerX="X";
    String playerO="O";
    String currentplayer=playerX;
    boolean gameover=false;
    int turns=0;
    
    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardwidth,boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.RED);
        textLabel.setForeground(Color.ORANGE);
        textLabel.setFont(new Font("Arial",Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("TicTacToe ( O or X)");
        textLabel.setOpaque(true);
        
        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        boardPanel.setBackground(Color.RED);
        frame.add(boardPanel);

        for(int r=0 ; r<3 ; r++){
            for(int c=0 ; c<3 ; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);
                tile.setBackground(Color.ORANGE);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
                
                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(gameover){
                            return;
                        }
                        JButton tile = (JButton)e.getSource();
                        if (tile.getText().equals("")){
                            tile.setText(currentplayer);
                            turns++;
                            checkwinner(); 
                            if(!gameover) {  
                                currentplayer = currentplayer.equals(playerX) ? playerO : playerX;
                                textLabel.setText(currentplayer + "'s Turn");
                            }    
                        }
                    }
                });
            }
        }
    }
    
    void checkwinner(){
        for(int r=0 ; r< 3 ; r++){
            if (board[r][0].getText().equals("")) {
                continue;
            }
            if (board[r][0].getText().equals(board[r][1].getText()) &&
                board[r][1].getText().equals(board[r][2].getText())){
                for(int i=0 ; i<3 ; i++){
                    setwinner(board[r][i]);
                }
                gameover=true;
                return;
            }
        }
        for (int c= 0 ; c<3 ; c++){
            if (board[0][c].getText()=="")
            continue;
            if (board[0][c].getText().equals(board[1][c].getText()) &&
            board[1][c].getText().equals(board[2][c].getText())){
              for(int i=0 ;i<3 ;i++){
                setwinner(board[i][c]);

              }
              gameover=true;
              return;
            }

        }

        if (board[0][0].getText() == board[1][1].getText() &&
        board[1][1].getText() == board[2][2].getText() &&
        board[0][0].getText() != "") {
        for (int i = 0; i < 3; i++) {
            setwinner(board[i][i]);
        }
        gameover = true;
        return;
    }
    if (board[0][2].getText() == board[1][1].getText() &&
    board[1][1].getText() == board[2][0].getText() &&
    board[0][2].getText() != "") {
    setwinner(board[0][2]);
    setwinner(board[1][1]);
    setwinner(board[2][0]);
    gameover = true;
    return;
}
if (turns == 9) {
    for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            setTie(board[r][c]);
        }
    }
    gameover = true;
}
}


    
    
    void setwinner(JButton tile){
        tile.setForeground(Color.WHITE);
        tile.setBackground(Color.GREEN);
        textLabel.setText(currentplayer + " wins");
    }
    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Draw!!");
    }
}
