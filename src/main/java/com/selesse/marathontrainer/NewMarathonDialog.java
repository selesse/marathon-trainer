package com.selesse.marathontrainer;

import com.selesse.marathontrainer.model.Settings;
import com.selesse.marathontrainer.resource.language.LanguageResource;
import com.selesse.marathontrainer.training.MarathonType;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.Date;

public class NewMarathonDialog extends JPanel {
    private JFileChooser fileChooser;
    private JButton fileChooserButton;
    private NewMarathonDialog currentPanel = this;
    private File chosenFile;

    public NewMarathonDialog(final LanguageResource resource, final Settings settings) {
        final ButtonGroup buttonGroup = new ButtonGroup();

        final JRadioButton halfMarathonRadioButton = new JRadioButton(resource.getHalfMarathonName());
        final JRadioButton fullMarathonRadioButton = new JRadioButton(resource.getFullMarathonName());

        buttonGroup.add(halfMarathonRadioButton);
        buttonGroup.add(fullMarathonRadioButton);

        final JButton finishButton = new JButton(resource.getFinishedText());
        final JXDatePicker datePicker = new JXDatePicker();

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date marathonDate = datePicker.getDate();
                MarathonType marathonType = null;
                if (halfMarathonRadioButton.isSelected()) {
                    marathonType = MarathonType.HALF;
                }
                if (fullMarathonRadioButton.isSelected()) {
                    marathonType = MarathonType.FULL;
                }

                if (marathonType == null) {
                   JOptionPane.showMessageDialog(currentPanel,
                           resource.getBadMarathonTypeMessage(),
                           resource.getBadMarathonTypeTitle(),
                           JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!marathonDate.after(new Date())) {
                    JOptionPane.showMessageDialog(currentPanel,
                            resource.getBadMarathonDateMessage(),
                            resource.getBadMarathonDateTitle(),
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (chosenFile == null || !chosenFile.exists()) {
                    JOptionPane.showMessageDialog(currentPanel,
                            resource.getBadTrainingFileMessage(),
                            resource.getBadTrainingFileTitle(),
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                settings.setMarathonType(marathonType);
                settings.setTrainingPlanPath(chosenFile.getAbsolutePath());
                settings.setMarathonDate(marathonDate);

                currentPanel.firePropertyChange("finished", false, true);
            }
        });


        fileChooser = new JFileChooser();
        fileChooserButton = new JButton(resource.getFileChooserText());

        fileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fileChooser.showOpenDialog(NewMarathonDialog.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    chosenFile = fileChooser.getSelectedFile();
                    fileChooserButton.setText(chosenFile.getName());
                }
            }
        });

        add(new JLabel(resource.getWhenMarathonText()));
        add(datePicker);
        add(halfMarathonRadioButton);
        add(fullMarathonRadioButton);
        add(fileChooserButton);
        add(finishButton);
    }
}
