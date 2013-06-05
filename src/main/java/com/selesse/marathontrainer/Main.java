package com.selesse.marathontrainer;

import com.selesse.marathontrainer.resource.language.EnglishLanguageResource;
import com.selesse.marathontrainer.resource.language.LanguageResource;
import com.selesse.marathontrainer.resource.language.LanguageResourceFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Entry point to the application.
 */
public class Main implements ActionListener {
    private static LanguageResource resources = new EnglishLanguageResource();
    private static JFrame mainFrame;

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
        Component contents = app.createComponents();
        mainFrame.getContentPane().add(contents, BorderLayout.CENTER);

        mainFrame.setPreferredSize(new Dimension(600, 300));

        mainFrame.pack();
        mainFrame.setVisible(true);
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

        ButtonGroup radioButtonGroup = new ButtonGroup();

        JRadioButtonMenuItem halfMarathonRadioMenuItem = new JRadioButtonMenuItem(resources.getHalfMarathonName());
        JRadioButtonMenuItem fullMarathonRadioMenuItem = new JRadioButtonMenuItem(resources.getFullMarathonName());

        halfMarathonRadioMenuItem.setSelected(true);

        radioButtonGroup.add(halfMarathonRadioMenuItem);
        radioButtonGroup.add(fullMarathonRadioMenuItem);

        fileMenu.add(halfMarathonRadioMenuItem);
        fileMenu.add(fullMarathonRadioMenuItem);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Component createComponents() {
        JPanel pane = new JPanel(new GridLayout(0, 1));

        return pane;
    }
}
