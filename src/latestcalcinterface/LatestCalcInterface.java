package latestcalcinterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.BorderFactory.createEmptyBorder;


public class LatestCalcInterface {
    JFrame CalcFrame          = new JFrame("Razor Studios");
    JTextArea DisplayArea     = new JTextArea("");
    JTextArea ResultArea      = new JTextArea("");
    JTextArea LogArea         = new JTextArea("Session History");
    JScrollPane ScrollPane    = new JScrollPane();
    JButton OperatorArea      = new JButton("");
    String TotalValueHidden   = "";
    String LastOperatorHidden = "";
    
    JButton Zero     = new JButton("0");
    JButton One      = new JButton("1");
    JButton Two      = new JButton("2");
    JButton Three    = new JButton("3");
    JButton Four     = new JButton("4");
    JButton Five     = new JButton("5");
    JButton Six      = new JButton("6");
    JButton Seven    = new JButton("7");
    JButton Eight    = new JButton("8");
    JButton Nine     = new JButton("9");
    JButton Multiply = new JButton("*");
    JButton Divide   = new JButton("/");
    JButton Minus    = new JButton("-");
    JButton Add      = new JButton("+");
    JButton Equals   = new JButton("=");
    JButton Dot      = new JButton(".");
    JButton Clear    = new JButton("Clear");
    JButton Back     = new JButton("Back");
    JButton Log      = new JButton("Log");
    JButton Return   = new JButton("<---");
    
    public LatestCalcInterface() {
        CalcFrame.setLayout(null);
        CalcFrame.setSize(245,325);
        ResultArea.setBounds(0,0,246,45);
        DisplayArea.setBounds(0,45,216,45);
        OperatorArea.setBounds(216,45,30,45);
        DisplayArea.setBackground(Color.GRAY);
        DisplayArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        DisplayArea.setEnabled(false);
        DisplayArea.setFont(new java.awt.Font("Comic Sans MS Italic", 3, 24));
        
        LogArea.setBounds(0,0,249,315);
        LogArea.setBackground(Color.GRAY);
        LogArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        LogArea.setEnabled(false);
        LogArea.setFont(new java.awt.Font("Comic Sans MS Italic", 3, 15));
        
        ScrollPane.setBounds(0, 30, 249, 297);
        ScrollPane.setViewportView(LogArea);
        
        Return.setVisible(false);
        
        ResultArea.setBackground(Color.GRAY);
        ResultArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ResultArea.setEnabled(false);
        ResultArea.setFont(new java.awt.Font("Comic Sans MS Italic", 3, 24));
        
        OperatorArea.setForeground(Color.GREEN);
        OperatorArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        OperatorArea.setBackground(Color.GRAY);
        OperatorArea.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        OperatorArea.setEnabled(false);
        OperatorArea.setFont(new java.awt.Font("Comic Sans MS Italic", 3, 24));
        //ResultArea.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
//        JButton TwoZero = new JButton("00");
//        JButton ThreeZero =  new JButton("000");
//        
//        TwoZero.setBounds(10,335,55,40);
//        ThreeZero.setBounds(70,335,55,40);
//        CalcFrame.add(TwoZero);
//        CalcFrame.add(ThreeZero);
//        
//        TwoZero.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                DisplayArea.setText(DisplayArea.getText() + "00");
//            }
//        });
//        
//        ThreeZero.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent evt) {
//                DisplayArea.setText(DisplayArea.getText() + "000");
//            }
//        });
//        
        
        //Remove Focus From Buttons
        RemoveFocus();
        //Set BackGround For Buttons
        SetBackground();
        //Change The Text Font For The Buttons
        ChangeFont();
        //Remove Border From Buttons
        SetBorder();

        CalcFrame.add(ResultArea);      //TextArea To Display The Result
        CalcFrame.add(DisplayArea);     //TextArea To Display The Typed Data
        CalcFrame.add(OperatorArea);    //TextArea To Display The Last Clicked Operator
        CalcFrame.add(ScrollPane);
        ScrollPane.setVisible(false);
        
        Multiply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                OperatorFunction("*");
            }
        });
        
        Divide.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                OperatorFunction("/");
            } 
        });
        
        Minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                OperatorFunction("-");
            }
        });
        
        Add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt){
                OperatorFunction("+");
           }
        });
        
        Equals.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(DisplayArea.getText().equals("-") || DisplayArea.getText().equals("-0.") || DisplayArea.getText().equals("-0")) {
               
                } else if(!"".equals(DisplayArea.getText())) {
                    if(!TotalValueHidden.equals("")) {
                        if(!OperatorArea.getText().equals("")) {
                            Double Total = 0.0;
                            if("*".equals(LastOperatorHidden)) {    
                                Total = Double.parseDouble(TotalValueHidden) * Double.parseDouble(DisplayArea.getText());
                            } else if("/".equals(LastOperatorHidden)) {    
                                Total = Double.parseDouble(TotalValueHidden) / Double.parseDouble(DisplayArea.getText());
                            } else if("-".equals(LastOperatorHidden)) {    
                                Total = Double.parseDouble(TotalValueHidden) - Double.parseDouble(DisplayArea.getText());
                            } else if("+".equals(LastOperatorHidden)) {    
                                Total = Double.parseDouble(TotalValueHidden) + Double.parseDouble(DisplayArea.getText());
                            }
                            
                            if(Total.toString().equals("NaN") || Total.toString().equals("Infinity") || Total.toString().equals("-Infinity")) {
                                ResultArea.setText("Unsupported Division");
                                DisplayArea.setText("");
                                TotalValueHidden = "";
                                LastOperatorHidden = "";
                                OperatorArea.setText("");
                            } else {
                                LogArea.setText(LogArea.getText() + "\n----------------------\n  " + ResultArea.getText() + "\n" + LastOperatorHidden + " " + DisplayArea.getText() + "\n");

                                int TotalLength = Total.toString().length();
                                if(TotalLength >= 3) {
                                    if(Total.toString().charAt(TotalLength-1) == '0' && Total.toString().charAt(TotalLength-2) == '.') {
                                        String TotalDivided = "";
                                        for(int i=0; i<TotalLength-2; i++) {
                                            TotalDivided = TotalDivided + Total.toString().charAt(i);
                                        }
                                        TotalValueHidden = TotalDivided;
                                        ResultArea.setText(TotalDivided);
                                    } else {
                                        TotalValueHidden = Total.toString();
                                        ResultArea.setText(Total.toString());
                                    }
                                } else {
                                    TotalValueHidden = Total.toString();
                                    ResultArea.setText(Total.toString());
                                }
                                
                                LogArea.setText(LogArea.getText() + "= " + TotalValueHidden);
                                
                                DisplayArea.setText("");
                                OperatorArea.setText("");
                            }
                        }
                    } else {
                        TotalValueHidden = DisplayArea.getText();
                        ResultArea.setText(DisplayArea.getText());
                        DisplayArea.setText("");
                        OperatorArea.setText("");
                    }
                }
            }
        });
        
        Dot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!DisplayArea.getText().equals("")) {
                    boolean isFound = false;
                    for(int i=0; i<DisplayArea.getText().length(); i++) {
                        if(DisplayArea.getText().charAt(i) == '.') {
                            isFound = true;
                        }
                    }
                    
                    if(isFound ==  false) {
                        if(DisplayArea.getText().equals("-")) {
                            DisplayArea.setText("-0.");
                        } else {
                            DisplayArea.setText(DisplayArea.getText() + ".");
                        }
                    }
                } else {
                    DisplayArea.setText("0.");
                }
            }
        });
        
        Clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DisplayArea.setText("");
                ResultArea.setText("");
                OperatorArea.setText("");
            }
        });
        
        Back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                int DAreaLength = DisplayArea.getText().length();
                String TempValue = "";
                if(DAreaLength > 0) {
                    for(int i=0; i<DAreaLength - 1; i++) {
                        TempValue = TempValue + DisplayArea.getText().charAt(i);
                    }
                    DisplayArea.setText(TempValue);
                }
            }
        });
        
        Log.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ViewSessionLogs();
            }
        });
        
        Return.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ExitLog();
            }
        });
        
        Zero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!"0".equals(DisplayArea.getText())) {
                    DisplayArea.setText(DisplayArea.getText() + "0");
                }
            }
        });
        
        One.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!"0".equals(DisplayArea.getText())) {
                    DisplayArea.setText(DisplayArea.getText() + "1");
                } else {
                    DisplayArea.setText("1");
                }
            }
        });
        
        Two.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!"0".equals(DisplayArea.getText())) {
                    DisplayArea.setText(DisplayArea.getText() + "2");
                } else {
                    DisplayArea.setText("2");
                }
            }
        });
        
        Three.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!"0".equals(DisplayArea.getText())) {
                    DisplayArea.setText(DisplayArea.getText() + "3");
                } else {
                    DisplayArea.setText("3");
                }
            }
        });
        
        Four.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!"0".equals(DisplayArea.getText())) {
                    DisplayArea.setText(DisplayArea.getText() + "4");
                } else {
                    DisplayArea.setText("4");
                }
            }
        });
        
        Five.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!"0".equals(DisplayArea.getText())) {
                    DisplayArea.setText(DisplayArea.getText() + "5");
                } else {
                    DisplayArea.setText("5");
                }
            }
        });
        
        Six.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!"0".equals(DisplayArea.getText())) {
                    DisplayArea.setText(DisplayArea.getText() + "6");
                } else {
                    DisplayArea.setText("6");
                }
            }
        });
        
        Seven.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!"0".equals(DisplayArea.getText())) {
                    DisplayArea.setText(DisplayArea.getText() + "7");
                } else {
                    DisplayArea.setText("7");
                }
            }
        });
        
        Eight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!"0".equals(DisplayArea.getText())) {
                    DisplayArea.setText(DisplayArea.getText() + "8");
                } else {
                    DisplayArea.setText("8");
                }
            }
        });
        
        Nine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(!"0".equals(DisplayArea.getText())) {
                    DisplayArea.setText(DisplayArea.getText() + "9");
                } else {
                    DisplayArea.setText("9");
                }
            }
        });
        /*
        The First Number Rows {7,8,9,Divide}
        Set The X-Axis, Y-Axis, Width and Height For The Buttons
        */
        Seven.setBounds(5, 100, 55, 40);
        Eight.setBounds(65,100,55,40);
        Nine.setBounds(125,100,55,40);
        Divide.setBounds(185,100,55,40);
        
        //Adding Buttons To The Frame
        CalcFrame.add(Seven);     
        CalcFrame.add(Eight);
        CalcFrame.add(Nine);
        CalcFrame.add(Divide);
        
        /*
        The Second Number Rows {4,5,6,Multiply}
        Set The X-Axis, Y-Axis, Width and Height For The Buttons
        */
        Four.setBounds(5, 145, 55, 40);
        Five.setBounds(65,145,55,40);
        Six.setBounds(125,145,55,40);
        Multiply.setBounds(185,145,55,40);
        
        //Adding Buttons To The Frame
        CalcFrame.add(Four);
        CalcFrame.add(Five);
        CalcFrame.add(Six);
        CalcFrame.add(Multiply);
        
        /* 
        The Third Number Rows {1,2,3,Minus}
        Set The X-Axis, Y-Axis, Width and Height For The Buttons
        */
        One.setBounds(5, 190, 55, 40);
        Two.setBounds(65,190,55,40);
        Three.setBounds(125,190,55,40);
        Minus.setBounds(185,190,55,40);
        
        //Adding Elements To The Frame
        CalcFrame.add(One);
        CalcFrame.add(Two);
        CalcFrame.add(Three);
        CalcFrame.add(Minus);
        
        /* 
        The Forth Number Rows {.,0,=,+}
        Set The X-Axis, Y-Axis, Width and Height For The Buttons
        */
        Dot.setBounds(5, 235, 55, 40);
        Zero.setBounds(65,235,55,40);
        Equals.setBounds(125,235,55,40);
        Add.setBounds(185,235,55,40);
        
        //Adding Elements To The Frame
        CalcFrame.add(Dot);
        CalcFrame.add(Zero);
        CalcFrame.add(Equals);
        CalcFrame.add(Add);
        
        //Clear And Back Buttons
        Clear.setBounds(5,280,85,40);
        Back.setBounds(95,280,85,40);
        Log.setBounds(185,280,55,40);
        Return.setBounds(0,0,80,30);
        
        CalcFrame.add(Clear);
        CalcFrame.add(Back);
        CalcFrame.add(Log);
        CalcFrame.add(Return);
        
        //Remove Border From All Buttons
        
        //Make The Frame Visible
        CalcFrame.setResizable(true);
        CalcFrame.setVisible(true);
    }
    
    public void RemoveFocus() {
        Zero.setFocusPainted(false);
        One.setFocusPainted(false);
        Two.setFocusPainted(false);
        Three.setFocusPainted(false);
        Four.setFocusPainted(false);
        Five.setFocusPainted(false);
        Six.setFocusPainted(false);
        Seven.setFocusPainted(false);
        Eight.setFocusPainted(false);
        Nine.setFocusPainted(false);
        Multiply.setFocusPainted(false);
        Divide.setFocusPainted(false);
        Minus.setFocusPainted(false);
        Add.setFocusPainted(false);
        Equals.setFocusPainted(false);
        Dot.setFocusPainted(false);
        Clear.setFocusPainted(false);
        Back.setFocusPainted(false);
        OperatorArea.setFocusPainted(false);
        Log.setFocusPainted(false);
        Return.setFocusPainted(false);
    }
    
    public void ViewSessionLogs() {
        ResultArea.setVisible(false);
        ScrollPane.setVisible(true);
        Return.setVisible(true);
        Log.setVisible(false);
        DisplayArea.setVisible(false);
        OperatorArea.setVisible(false);
    }
    
    public void ExitLog() {
        ResultArea.setVisible(true);
        ScrollPane.setVisible(false);
        Return.setVisible(false);
        Log.setVisible(true);
        DisplayArea.setVisible(true);
        OperatorArea.setVisible(true);
    }
    
    public void SetBackground() {
        Zero.setBackground(Color.WHITE);
        One.setBackground(Color.WHITE);
        Two.setBackground(Color.WHITE);
        Three.setBackground(Color.WHITE);
        Four.setBackground(Color.WHITE);
        Five.setBackground(Color.WHITE);
        Six.setBackground(Color.WHITE);
        Seven.setBackground(Color.WHITE);
        Eight.setBackground(Color.WHITE);
        Nine.setBackground(Color.WHITE);
        Divide.setBackground(Color.WHITE);
        Multiply.setBackground(Color.WHITE);
        Minus.setBackground(Color.WHITE);
        Add.setBackground(Color.WHITE);
        Equals.setBackground(Color.WHITE);
        Dot.setBackground(Color.WHITE);
        Clear.setBackground(Color.WHITE);
        Back.setBackground(Color.WHITE);
        Log.setBackground(Color.WHITE);
        Return.setBackground(Color.WHITE);
    }
    
    public void ChangeFont() {
        Zero.setFont(new Font("Comic Sans MS Italic", 3, 24));
        One.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Two.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Three.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Four.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Five.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Six.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Seven.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Eight.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Nine.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Multiply.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Divide.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Minus.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Add.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Dot.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Clear.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Back.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Equals.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Log.setFont(new Font("Comic Sans MS Italic", 3, 24));
        Return.setFont(new Font("Comic Sans MS Italic", 3, 24));
    }
    
    public void SetBorder() {
        Zero.setBorder(createEmptyBorder(0, 0, 0, 0));
        One.setBorder(createEmptyBorder(0, 0, 0, 0));
        Two.setBorder(createEmptyBorder(0, 0, 0, 0));
        Three.setBorder(createEmptyBorder(0, 0, 0, 0));
        Four.setBorder(createEmptyBorder(0, 0, 0, 0));
        Five.setBorder(createEmptyBorder(0, 0, 0, 0));
        Six.setBorder(createEmptyBorder(0, 0, 0, 0));
        Seven.setBorder(createEmptyBorder(0, 0, 0, 0));
        Eight.setBorder(createEmptyBorder(0, 0, 0, 0));
        Nine.setBorder(createEmptyBorder(0, 0, 0, 0));
        Multiply.setBorder(createEmptyBorder(0, 0, 0, 0));
        Divide.setBorder(createEmptyBorder(0, 0, 0, 0));
        Minus.setBorder(createEmptyBorder(0, 0, 0, 0));
        Add.setBorder(createEmptyBorder(0, 0, 0, 0));
        Dot.setBorder(createEmptyBorder(0, 0, 0, 0));
        Clear.setBorder(createEmptyBorder(0, 0, 0, 0));
        Back.setBorder(createEmptyBorder(0, 0, 0, 0));
        Equals.setBorder(createEmptyBorder(0, 0, 0, 0));
        OperatorArea.setBorder(createEmptyBorder(0, 0, 0, 0));
        Log.setBorder(createEmptyBorder(0, 0, 0, 0));
        Return.setBorder(createEmptyBorder(0, 0, 0, 0));
    }
    
    public void OperatorFunction(String Op) {
        Double Total = 0.0;
        boolean isCharFound = false;
        for(int i=0; i<ResultArea.getText().length(); i++) {
            char CH = ResultArea.getText().charAt(i);
            if(CH == '0' || CH == '1' || CH == '2' || CH == '3' || CH == '4' ||
                    CH == '5' || CH == '6' || CH == '7' || CH == '8' || CH == '9' || CH == '.' || CH == '-' || CH == 'E') {
            } else {
                isCharFound = true;
                break;
            }
        }
        
        if(DisplayArea.getText().equals("-") || DisplayArea.getText().equals("-0.") || DisplayArea.getText().equals("-0")) {
               
        } else if(!DisplayArea.getText().equals("")) {
            if(!OperatorArea.getText().equals("")) {
                if(!LastOperatorHidden.equals("")) {
                    if(!TotalValueHidden.equals("")) {
                        if(isCharFound == false) {
                            if("*".equals(LastOperatorHidden)) {    
                                Total = Double.parseDouble(TotalValueHidden) * Double.parseDouble(DisplayArea.getText());
                            } else if("/".equals(LastOperatorHidden)){
                                Total = Double.parseDouble(TotalValueHidden) / Double.parseDouble(DisplayArea.getText());
                            }else if("-".equals(LastOperatorHidden)) {    
                                Total = Double.parseDouble(TotalValueHidden) - Double.parseDouble(DisplayArea.getText());
                            } else if("+".equals(LastOperatorHidden)) {    
                                Total = Double.parseDouble(TotalValueHidden) + Double.parseDouble(DisplayArea.getText());
                            }
                            
                            if(Total.toString().equals("NaN") || Total.toString().equals("Infinity") || Total.toString().equals("-Infinity")) {
                                ResultArea.setText("Unsupported Division");
                                DisplayArea.setText("");
                                TotalValueHidden = "";
                                LastOperatorHidden = "";
                                OperatorArea.setText("");
                            } else {
                                LogArea.setText(LogArea.getText() + "\n----------------------\n  " + ResultArea.getText() + "\n" + LastOperatorHidden + " " + DisplayArea.getText() + "\n");

                                int TotalLength = Total.toString().length();
                                if(TotalLength >= 3) {
                                    if(Total.toString().charAt(TotalLength-1) == '0' && Total.toString().charAt(TotalLength-2) == '.') {
                                        String TotalDivided = "";
                                        for(int i=0; i<TotalLength-2; i++) {
                                            TotalDivided = TotalDivided + Total.toString().charAt(i);
                                        }
                                        TotalValueHidden = TotalDivided;
                                        ResultArea.setText(TotalDivided);
                                    } else {
                                        TotalValueHidden = Total.toString();
                                        ResultArea.setText(Total.toString());
                                    }
                                } else {
                                    TotalValueHidden = Total.toString();
                                    ResultArea.setText(Total.toString());
                                }
                                
                                LogArea.setText(LogArea.getText() + "= " + TotalValueHidden);
                                
                                DisplayArea.setText("");
                                OperatorArea.setText(Op);
                            }
                        } else {
                            ResultArea.setText(DisplayArea.getText());
                            DisplayArea.setText("");
                            TotalValueHidden = DisplayArea.getText();
                            OperatorArea.setText("");
                        }
                    } else {
                        TotalValueHidden = DisplayArea.getText();
                        ResultArea.setText(DisplayArea.getText());
                        DisplayArea.setText("");
                        OperatorArea.setText(Op);
                    }
                } else {
                    ResultArea.setText(DisplayArea.getText());
                    OperatorArea.setText(Op);
                    LastOperatorHidden = Op;
                    TotalValueHidden = DisplayArea.getText();
                    DisplayArea.setText("");

                }
                DisplayArea.setText("");
                LastOperatorHidden = Op;
            } else {
                if(!ResultArea.getText().equals("")) {
                    if(isCharFound == true) {
                        if(DisplayArea.getText().equals("") || DisplayArea.getText().equals("0")) {
                            ResultArea.setText("");
                        } else {
                            ResultArea.setText(DisplayArea.getText());
                            OperatorArea.setText(Op);
                            TotalValueHidden = DisplayArea.getText();
                        }
                        DisplayArea.setText("");
                    } else {
                        ResultArea.setText(ResultArea.getText());
                        OperatorArea.setText(Op);
                    }
                }
                
                if(ResultArea.getText().equals("")) {
                    TotalValueHidden = DisplayArea.getText();
                    ResultArea.setText(DisplayArea.getText());
                    OperatorArea.setText(Op);
                    DisplayArea.setText("");
                }
                
                LastOperatorHidden = Op;
            }
        } else {
            if(Op.equals("-")) {
                DisplayArea.setText("-");
            } else {
                if(OperatorArea.getText().equals("") && !ResultArea.getText().equals("")) {
                    if(isCharFound == true) {
                        if(DisplayArea.getText().equals("") || DisplayArea.getText().equals("0")) {
                            ResultArea.setText("");
                        } else {
                            ResultArea.setText(DisplayArea.getText());
                            OperatorArea.setText(Op);
                        }
                    } else {
                        ResultArea.setText(ResultArea.getText());
                        OperatorArea.setText(Op);
                    }

                    DisplayArea.setText("");
                    LastOperatorHidden = Op;
                } else {
                    if(!OperatorArea.getText().equals("")) {
                        OperatorArea.setText(Op);
                        LastOperatorHidden = Op;
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        new LatestCalcInterface();
    }   
}
