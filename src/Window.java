import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferStrategy;

public class Window extends JFrame{
    private Cube cube;
    Point drawOrigin;

    public static final int WIDTH = 800;
    public static final int HEIGHT = (WIDTH / 20) * 9;
    Dimension panelDimension = new Dimension(200,HEIGHT);
    public static String title = "3D Cube";

    public JPanel rotatePanel;
    public JSlider rotateX;
    public JSlider rotateY;
    public JSlider rotateZ;
    public Button reFreshBtn;

    public JPanel filledPanel;
    public Checkbox frontCB;
    public Checkbox backCB;
    public Checkbox topCB;
    public Checkbox bottomCB;
    public Checkbox rightCB;
    public Checkbox leftCB;


    public Window() {
        super(title);
        getContentPane().setBackground(Color.WHITE);
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        init();

    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        Render(g2d);
        g2d.dispose();
    }

    public void Render(Graphics2D g2d){
        filled();
        ChangeRotate();
        cube.DrawCube(drawOrigin,g2d);
    }
    public void filled(){
        cube.setFillFront(frontCB.getState());
        cube.setFillBack(backCB.getState());
        cube.setFillTop(topCB.getState());
        cube.setFillBottom(bottomCB.getState());
        cube.setFillRight(rightCB.getState());
        cube.setFillLeft(leftCB.getState());
    }
    public void ChangeRotate(){
        cube.RotateCubeX(cube.xRotation - rotateX.getValue());
        cube.xRotation = rotateX.getValue();

        cube.RotateCubeY(cube.yRotation - rotateY.getValue());
        cube.yRotation = rotateY.getValue();

        cube.RotateCubeZ(cube.zRotation - rotateZ.getValue());
        cube.zRotation = rotateZ.getValue();
    }
    public void init() {
        cube = new Cube(200, 300, 250);
        drawOrigin = new Point(WIDTH/2, HEIGHT/2);


        inItRotatePanel();
        inItFilledPanel();

        this.add(rotatePanel, BorderLayout.EAST);
        this.add(filledPanel, BorderLayout.WEST);
    }


    void inItFilledPanel()
    {
        filledPanel = new JPanel();
        filledPanel.setPreferredSize(panelDimension);

        filledPanel.setLayout((new javax.swing.BoxLayout(filledPanel, javax.swing.BoxLayout.Y_AXIS)));
        filledPanel.setBorder(BorderFactory.createEmptyBorder(10,70,10,10));

        frontCB = new Checkbox("Front");
        frontCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                validate();
                repaint();
            }
        });
        backCB = new Checkbox("Back");
        backCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                validate();
                repaint();
            }
        });
        topCB = new Checkbox("Top");
        topCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                validate();
                repaint();
            }
        });
        bottomCB = new Checkbox("Bottom");
        bottomCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                validate();
                repaint();
            }
        });
        rightCB = new Checkbox("Right");
        rightCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                validate();
                repaint();
            }
        });
        leftCB = new Checkbox("Left");
        leftCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                validate();
                repaint();
            }
        });

        filledPanel.add(frontCB);
        filledPanel.add(backCB);
        filledPanel.add(topCB);
        filledPanel.add(bottomCB);
        filledPanel.add(rightCB);
        filledPanel.add(leftCB);
    }
    void inItRotatePanel()
    {
        rotatePanel = new JPanel();


        rotatePanel.setPreferredSize(panelDimension);


        Label x = new Label("X");
        Label y = new Label("Y");
        Label z = new Label("Z");


        rotatePanel.add(x);
        rotateX = new JSlider(-360,360);
        rotateX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validate();
                repaint();
            }
        });


        rotatePanel.add(rotateX);

        rotatePanel.add(y);
        rotateY = new JSlider(-360,360);
        rotatePanel.add(rotateY);
        rotateY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validate();
                repaint();
            }
        });

        rotatePanel.add(z);
        rotateZ = new JSlider(-360,360);
        rotateZ.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                validate();
                repaint();
            }
        });
        rotatePanel.add(rotateZ);



        reFreshBtn = new Button();
        reFreshBtn.setLabel("Refresh");
        reFreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotateX.setValue(0);
                rotateY.setValue(0);
                rotateZ.setValue(0);

                frontCB.setState(false);
                backCB.setState(false);
                leftCB.setState(false);
                rightCB.setState(false);
                bottomCB.setState(false);
                topCB.setState(false);

                cube = new Cube(200, 300, 250);

                validate();
                repaint();

            }
        });
        rotatePanel.add(reFreshBtn);



    }

    public static void main(String args[]) {
        @SuppressWarnings("unused")
        Window window = new Window();
        window.setVisible(true);
    }
}
