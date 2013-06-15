package com.selesse.marathontrainer;

import com.selesse.marathontrainer.model.Settings;
import com.selesse.marathontrainer.resource.files.InvalidTrainingFileException;
import com.selesse.marathontrainer.resource.files.TrainingPlanLoader;
import com.selesse.marathontrainer.resource.language.Language;
import com.selesse.marathontrainer.resource.language.LanguageResource;
import com.selesse.marathontrainer.resource.language.LanguageResourceFactory;
import com.selesse.marathontrainer.training.TrainingActivity;
import com.selesse.marathontrainer.training.TrainingPlan;
import org.jdesktop.swingx.JXMonthView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Entry point to the application.
 */
public class Main {
    private LanguageResource resources;
    private JFrame mainFrame;
    private Settings settings;
    private TrainingPlan trainingPlan;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    private void start() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                settings = loadOrInitializeSettings();
                createAndShowGUI(settings.getLanguage());
            }
        });
    }

    private void createAndShowGUI(Language language) {
        resources = LanguageResourceFactory.createResource(language);

        setSystemLookAndFeel();

        mainFrame = new JFrame(resources.getProgramName());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.setJMenuBar(createMenuBar());
        mainFrame.setPreferredSize(new Dimension(800, 400));

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                save();
            }
        });

        showFirstScreen();
    }

    private void showFirstScreen() {
        if (settings.getMarathonDate() == null) {
            showFileNewHint();
        }
        else {
            showMarathonTrainer();
        }
    }

    private void showFileNewHint() {
        JPanel pane = new JPanel(new GridLayout(0, 1));
        JLabel startHintText = new JLabel(resources.getStartHintText());
        startHintText.setHorizontalAlignment(SwingConstants.CENTER);

        pane.add(startHintText);

        mainFrame.getContentPane().add(pane, BorderLayout.CENTER);
    }

    private void showMarathonTrainer() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().repaint();
        JPanel innerMarathonPanel = new JPanel(new GridBagLayout());

        // set up the month view
        final JXMonthView monthView = new JXMonthView(resources.getLanguage().getLocale());
        monthView.setFirstDayOfWeek(Calendar.SUNDAY);
        monthView.setTraversable(true);
        monthView.setBoxPaddingX(5);
        monthView.setBoxPaddingY(10);
        monthView.setBackground(innerMarathonPanel.getBackground());

        final JLabel label = new JLabel();

        monthView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = monthView.getSelectionDate();
                TrainingActivity trainingActivity = trainingPlan.getActivityForDate(selectedDate);

                label.setText(trainingActivity.getPrintFriendlyString(resources));

            }
        });

        // set up the label by getting today's activity
        if (trainingPlan == null) {
            try {
                trainingPlan = TrainingPlanLoader.loadPlan(settings.getMarathonType(), settings.getTrainingPlanPath());
                trainingPlan.setMarathonDate(settings.getMarathonDate());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidTrainingFileException e) {
                e.printStackTrace();
            }
        }
        TrainingActivity todaysActivity = trainingPlan.getActivityForDate(new Date());

        label.setText(todaysActivity.getPrintFriendlyString(resources));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        // set the constraints for the monthView
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridwidth = 3;
        constraints.weightx = 1.0;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(20, 0, 0, 0);

        innerMarathonPanel.add(monthView, constraints);

        // set the constraints for the label
        constraints.gridwidth = 3;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.weighty = 1.0;
        constraints.insets = new Insets(0, 0, 0, 0);

        innerMarathonPanel.add(label, constraints);

        mainFrame.getContentPane().add(innerMarathonPanel);
        mainFrame.setVisible(true);
    }

    private void save() {
        if (settings != null) {
            try {
                String settingsPath = Settings.getSettingsLocation();

                File settingsFile = new File(settingsPath);

                if (!settingsFile.createNewFile()) {
                    // ignore if the delete fails for now
                    settingsFile.delete();
                    settingsFile.createNewFile();
                }
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(settingsFile));
                oos.writeObject(settings);
                oos.flush();
                oos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Settings loadOrInitializeSettings() {
        try {
            File settingsFile = new File(Settings.getSettingsLocation());
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(settingsFile));

            settings = (Settings) ois.readObject();

            ois.close();

            return settings;
        }
        catch (FileNotFoundException e) {
            // cool, do nothing
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(mainFrame, resources.getLoadErrorMessage(), resources.getLoadErrorTitle(),
                    JOptionPane.ERROR_MESSAGE);
        }

        return new Settings(Language.ENGLISH);
    }

    private void setSystemLookAndFeel() {
        final String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createSettingsMenu());

        return menuBar;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu(resources.getMenuFileName());
        fileMenu.setMnemonic(resources.getMenuFileMnemonic());

        JMenuItem newMarathonMenuItem = new JMenuItem(resources.getNewMarathonName());
        newMarathonMenuItem.setMnemonic(resources.getNewMarathonMnemonic());

        newMarathonMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewMarathonDialog();
            }
        });

        fileMenu.add(newMarathonMenuItem);
        fileMenu.addSeparator();

        JMenuItem exitMenuItem = new JMenuItem(resources.getMenuExitName());
        exitMenuItem.setMnemonic(resources.getMenuExitMnemonic());
        fileMenu.add(exitMenuItem);

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        return fileMenu;
    }

    private void createNewMarathonDialog() {
        final JDialog dialog = new JDialog(mainFrame, resources.getNewMarathonName(), true);
        final NewMarathonDialog marathonDialog = new NewMarathonDialog(resources, settings);

        dialog.add(marathonDialog);

        dialog.setPreferredSize(new Dimension(200, 200));

        marathonDialog.addPropertyChangeListener("finished", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                dialog.dispose();
                try {
                    trainingPlan = TrainingPlanLoader.loadPlan(settings.getMarathonType(),
                            settings.getTrainingPlanPath());
                    trainingPlan.setMarathonDate(settings.getMarathonDate());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (InvalidTrainingFileException e) {
                    e.printStackTrace();
                }
                showMarathonTrainer();
            }
        });

        dialog.pack();
        dialog.setVisible(true);
    }

    private JMenu createSettingsMenu() {
        JMenu settingsMenu = new JMenu(resources.getMenuSettingsName());
        settingsMenu.setMnemonic(resources.getMenuSettingsMnemonic());

        JMenuItem languageMenuItem = new JMenuItem(resources.getMenuLanguageName());
        languageMenuItem.setMnemonic(resources.getMenuLanguageMnemonic());

        settingsMenu.add(languageMenuItem);

        languageMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] supportedLanguages = Language.getSupportedLanguages();

                Language currentLanguage = resources.getLanguage();

                String chosenLanguage = (String) JOptionPane.showInputDialog(mainFrame,
                        resources.getLanguageChooserText(),
                        resources.getLanguageChooserTitle(),
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        supportedLanguages,
                        supportedLanguages[0]);

                if (chosenLanguage != null) {
                    Language newLanguage = Language.getLanguageFromLocaleString(chosenLanguage);
                    if (currentLanguage != newLanguage) {
                        settings.setLanguage(newLanguage);

                        mainFrame.setVisible(false);
                        mainFrame.dispose();
                        mainFrame = null;
                        createAndShowGUI(newLanguage);
                    }
                }
            }
        });

        return settingsMenu;
    }
}
