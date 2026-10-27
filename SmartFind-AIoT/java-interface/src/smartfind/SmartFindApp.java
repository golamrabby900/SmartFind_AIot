package smartfind;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SmartFindApp {
    private final SmartFindService service = new SmartFindService();
    private final DefaultListModel<String> reportModel = new DefaultListModel<>();
    private final DefaultListModel<String> matchModel = new DefaultListModel<>();
    private long lastCreatedId = -1;

    private void show() {
        JFrame frame = new JFrame("SmartFind AIoT — Campus Lost & Found");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(980, 620);
        frame.setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        JComboBox<String> type = new JComboBox<>(new String[]{"LOST", "FOUND"});
        JTextField category = new JTextField("Wallet");
        JTextField description = new JTextField("Black leather wallet with student card");
        JTextField location = new JTextField("Central Library");
        JButton submit = new JButton("Submit report");
        JButton matches = new JButton("Find possible matches");
        form.add(new JLabel("Report type")); form.add(type);
        form.add(new JLabel("Category")); form.add(category);
        form.add(new JLabel("Description")); form.add(description);
        form.add(new JLabel("Approximate location")); form.add(location);
        form.add(submit); form.add(matches);

        JList<String> reports = new JList<>(reportModel);
        JList<String> candidates = new JList<>(matchModel);
        JPanel lists = new JPanel(new GridLayout(1, 2, 10, 10));
        lists.add(panelWithTitle("Reports", reports));
        lists.add(panelWithTitle("Suggested matches (advisory only)", candidates));

        JLabel note = new JLabel("Demo data is in memory. Human verification is required before handover.");
        note.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        submit.addActionListener(e -> {
            try {
                ItemReport report = service.createReport((String) type.getSelectedItem(),
                        category.getText().trim(), description.getText().trim(), location.getText().trim());
                lastCreatedId = report.getReportId();
                reportModel.addElement(report.toString());
                JOptionPane.showMessageDialog(frame, "Report saved in prototype: #" + report.getReportId());
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Validation", JOptionPane.WARNING_MESSAGE);
            }
        });
        matches.addActionListener(e -> {
            ItemReport source = null;
            for (ItemReport r : service.getReports()) if (r.getReportId() == lastCreatedId) source = r;
            matchModel.clear();
            if (source == null) {
                matchModel.addElement("Submit a report first.");
                return;
            }
            List<MatchCandidate> found = service.findMatches(source);
            if (found.isEmpty()) matchModel.addElement("No prototype matches found yet.");
            else found.forEach(m -> matchModel.addElement(m.toString()));
        });

        frame.add(form, BorderLayout.NORTH);
        frame.add(lists, BorderLayout.CENTER);
        frame.add(note, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
    private JPanel panelWithTitle(String title, JList<String> list) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(title));
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        return panel;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SmartFindApp().show());
    }
}
