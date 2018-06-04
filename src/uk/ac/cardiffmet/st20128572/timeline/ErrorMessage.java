/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.cardiffmet.st20128572.timeline;

import javax.swing.*;  
import java.awt.*;  
import java.awt.event.*;  
/**
 *
 * @author John
 */
public class ErrorMessage 
{
    private static JDialog dialog;
    private String message;
    
    public ErrorMessage(String message)
    {
        this.message = message;
        JFrame frame = new JFrame();
        dialog = new JDialog(frame, message, true);
        dialog.setLayout(new FlowLayout());
        
        JButton button = new JButton("OK");
        
        button.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                ErrorMessage.dialog.setVisible(false);  
            }  
        });
        
        dialog.add(new JLabel(message));
        dialog.add(button);
        dialog.setSize(300,300);
        dialog.setVisible(true);
        
        frame.add(dialog);
        frame.setVisible(true);
    }
}

