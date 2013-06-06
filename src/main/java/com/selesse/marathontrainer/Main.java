package com.selesse.marathontrainer;

import com.selesse.marathontrainer.model.Settings;
import com.selesse.marathontrainer.resource.language.EnglishLanguageResource;
import com.selesse.marathontrainer.resource.language.LanguageResource;
import com.selesse.marathontrainer.resource.language.LanguageResourceFactory;
import com.selesse.marathontrainer.training.MarathonType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

/**
 * Entry point to the application.
 */
public class Main {
    private static LanguageResource resources = new EnglishLanguageResource();
    private static JFrame mainFrame;
    private static Settings settings;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        setSystemLookAndFeel();

        mainFrame = new JFrame(resources.getProgramName());
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.setJMenuBar(createMenuBar());

        Main app = new Main();

        mainFrame.setPreferredSize(new Dimension(600, 300));

        mainFrame.pack();
        mainFrame.setVisible(true);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                save();
            }
        });

        if (loadSettings()) {
            showMarathonTrainer();
        }
        else {
            showFileNewHint();
        }
    }

    private static void showFileNewHint() {
        JPanel pane = new JPanel(new GridLayout(0, 1));
        JLabel startHintText = new JLabel(resources.getStartHintText());
        startHintText.setHorizontalAlignment(SwingConstants.CENTER);

        pane.add(startHintText);

        mainFrame.getContentPane().add(pane, BorderLayout.CENTER);
    }

    private static void showMarathonTrainer() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(new JPanel(new GridLayout(0, 1)), BorderLayout.CENTER);
    }

    private static void save() {
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

    private static boolean loadSettings() {
        try {
            File file = new File("training-plan/settings");
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            settings = (Settings) ois.readObject();

            ois.close();

            return true;
        }
        catch (FileNotFoundException e) {
            // cool, do nothing
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(mainFrame, resources.getLoadErrorMessage(), resources.getLoadErrorTitle(),
                    JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    private static void setSystemLookAndFeel() {
        final String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
        try {
            UIManager.setLookAndFeel(lookAndFeel);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame.setDefaultLookAndFeelDecorated(true);
    }

    private static JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(createFileMenu());
        menuBar.add(createSettingsMenu());

        return menuBar;
    }

    private static JMenu createFileMenu() {
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

    private static void createNewMarathonDialog() {
        if (settings == null) {
            settings = new Settings(resources.getLanguageName());
        }

        final JDialog dialog = new JDialog(mainFrame, resources.getNewMarathonName(), true);
        final NewMarathonDialog marathonDialog = new NewMarathonDialog(resources, settings);

        dialog.add(marathonDialog);

        dialog.setPreferredSize(new Dimension(200, 200));

        marathonDialog.addPropertyChangeListener("finished", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                dialog.setVisible(false);
                showMarathonTrainer();
            }
        });

        dialog.pack();
        dialog.setVisible(true);
    }

    private static JMenu createSettingsMenu() {
        JMenu settingsMenu = new JMenu(resources.getMenuSettingsName());
        settingsMenu.setMnemonic(resources.getMenuSettingsMnemonic());

        JMenuItem languageMenuItem = new JMenuItem(resources.getMenuLanguageName());
        languageMenuItem.setMnemonic(resources.getMenuLanguageMnemonic());

        settingsMenu.add(languageMenuItem);

        languageMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] supportedLanguages = resources.getSupportedLanguages();

                String currentLanguage = resources.getLanguageName();

                String chosenLanguage = (String) JOptionPane.showInputDialog(mainFrame,
                        resources.getLanguageChooserText(),
                        resources.getLanguageChooserTitle(),
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        supportedLanguages,
                        supportedLanguages[0]);

                if (chosenLanguage != null) {
                    if (!currentLanguage.equals(chosenLanguage)) {
                        resources = LanguageResourceFactory.createResource(chosenLanguage);
                        createAndShowGUI();
                    }
                }
            }
        });

        return settingsMenu;
    }
}
