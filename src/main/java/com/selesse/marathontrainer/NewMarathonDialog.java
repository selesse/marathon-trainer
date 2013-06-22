package com.selesse.marathontrainer;

import com.selesse.marathontrainer.model.Settings;
import com.selesse.marathontrainer.resource.files.TrainingPlanLoader;
import com.selesse.marathontrainer.resource.language.LanguageResource;
import com.selesse.marathontrainer.training.MarathonType;
import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;

public class NewMarathonDialog extends JPanel {
    private final JFileChooser fileChooser;
    private final JButton fileChooserButton;
    private final NewMarathonDialog currentPanel = this;
    private File chosenFile;

    public NewMarathonDialog(final LanguageResource resource, final Settings settings) {
        super(new GridBagLayout());

        final JLabel whenMarathonLabel = new JLabel(resource.getWhenMarathonText());
        whenMarathonLabel.setHorizontalAlignment(SwingConstants.CENTER);

        final ButtonGroup buttonGroup = new ButtonGroup();

        final JRadioButton halfMarathonRadioButton = new JRadioButton(resource.getHalfMarathonName());
        final JRadioButton fullMarathonRadioButton = new JRadioButton(resource.getFullMarathonName());

        buttonGroup.add(halfMarathonRadioButton);
        buttonGroup.add(fullMarathonRadioButton);

        halfMarathonRadioButton.setSelected(true);

        final JButton finishButton = new JButton(resource.getFinishedText());
        final JXDatePicker datePicker = new JXDatePicker(resource.getLanguage().getLocale());

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

                if (isInvalidTrainingPlanFile()) {
                    warnBadFile(resource);
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

                    if (isInvalidTrainingPlanFile()) {
                        warnBadFile(resource);
                        return;
                    }
                    fileChooserButton.setText(chosenFile.getName());
                }
            }
        });

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 2;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(whenMarathonLabel, constraints);

        constraints.gridy = 1;
        add(datePicker, constraints);

        constraints.gridy = 2;
        constraints.gridwidth = 1;
        add(halfMarathonRadioButton, constraints);

        constraints.gridx = 1;
        add(fullMarathonRadioButton, constraints);

        constraints.gridwidth = 2;
        constraints.gridy = 3;
        constraints.gridx = 0;
        add(fileChooserButton, constraints);

        constraints.gridy = 4;
        add(finishButton, constraints);
    }

    private boolean isInvalidTrainingPlanFile() {
        return chosenFile == null || !chosenFile.exists() || !TrainingPlanLoader.isValidTrainingPlan(chosenFile);
    }


    private boolean isBadMarathonDate(Date date) {
        return date == null || date.before(new Date());
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
}
