package com.selesse.marathontrainer;

import com.selesse.marathontrainer.model.Settings;
import com.selesse.marathontrainer.resource.files.InvalidTrainingFileException;
import com.selesse.marathontrainer.resource.files.TrainingPlanLoader;
import com.selesse.marathontrainer.resource.language.LanguageResource;
import com.selesse.marathontrainer.training.MarathonType;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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

        halfMarathonRadioButton.setSelected(true);

        final JButton finishButton = new JButton(resource.getFinishedText());
        final JXDatePicker datePicker = new JXDatePicker();

        datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isBadMarathonDate(datePicker.getDate())) {
                    warnBadMarathonDate(resource);
                    datePicker.setDate(null);
                }
            }
        });

        finishButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
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

                if (isBadMarathonDate(marathonDate)) {
                    warnBadMarathonDate(resource);
                    return;
                }

                try {
                    if (chosenFile == null || !chosenFile.exists()) {
                        warnBadFile(resource);
                    }
                    TrainingPlanLoader.loadPlan(marathonType, chosenFile.getPath());
                }
                catch (FileNotFoundException e) {
                    warnBadFile(resource);
                    return;
                }
                catch (InvalidTrainingFileException e) {
                    warnInvalidFileType(resource);
                    return;
                }

                settings.setMarathonType(marathonType);
                settings.setTrainingPlanPath(chosenFile.getAbsolutePath());
                settings.setMarathonDate(marathonDate);

                currentPanel.firePropertyChange("finished", false, true);
            }
        });


        fileChooser = new JFileChooser(Settings.getSettingsDirectory());
        fileChooserButton = new JButton(resource.getFileChooserText());

        fileChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                int returnVal = fileChooser.showOpenDialog(NewMarathonDialog.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    chosenFile = fileChooser.getSelectedFile();

                    try {
                        chosenFile = fileChooser.getSelectedFile();
                        if (chosenFile != null && chosenFile.exists()) {
                            TrainingPlanLoader.loadPlan(MarathonType.FULL, chosenFile.getAbsolutePath());
                        }
                        fileChooserButton.setText(chosenFile.getName());
                    }
                    catch (InvalidTrainingFileException e) {
                        warnInvalidFileType(resource);
                    } catch (FileNotFoundException e) {
                        warnBadFile(resource);
                    }
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

    private void warnInvalidFileType(LanguageResource resource) {
        JOptionPane.showMessageDialog(currentPanel,
                resource.getBadFileTypeMessage(),
                resource.getBadFileTypeTitle(),
                JOptionPane.ERROR_MESSAGE);
    }

    private void warnBadMarathonDate(LanguageResource resource) {
        JOptionPane.showMessageDialog(currentPanel,
                resource.getBadMarathonDateMessage(),
                resource.getBadMarathonDateTitle(),
                JOptionPane.ERROR_MESSAGE);
    }

    private void warnBadFile(LanguageResource resource) {
        JOptionPane.showMessageDialog(currentPanel,
                resource.getBadTrainingFileMessage(),
                resource.getBadTrainingFileTitle(),
                JOptionPane.ERROR_MESSAGE);
    }

    private boolean isBadMarathonDate(Date date) {
        return date == null || date.before(new Date());
    }

}
