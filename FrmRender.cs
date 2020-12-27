//===============================================
//| Downloaded from                             |
//| Visual C# Kicks - http://www.vcskicks.com/  |
//===============================================
using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Drawing.Drawing2D;
using System.Text;
using System.Windows.Forms;

namespace _DCubeNoGimbalLock
{
    public partial class FrmRender : Form
    {
        public FrmRender()
        {
            InitializeComponent();
        }

        Math3D.Cube mainCube;
        Point drawOrigin;

        private void FrmRender_Load(object sender, EventArgs e)
        {
            mainCube = new Math3D.Cube(100, 200, 75);
            drawOrigin = new Point(pictureBox1.Width / 2, pictureBox1.Height / 2);
        }

        private void Render()
        {
            mainCube.RotateX = (float)tX.Value;
            mainCube.RotateY = (float)tY.Value;
            mainCube.RotateZ = (float)tZ.Value;

            pictureBox1.Image = mainCube.DrawCube(drawOrigin);
        }

        private void tX_Scroll(object sender, EventArgs e)
        {
            this.Refresh();
        }

        private void tY_Scroll(object sender, EventArgs e)
        {
            this.Refresh();
        }

        private void tZ_Scroll(object sender, EventArgs e)
        {
            this.Refresh();
        }

        private void btnReset_Click(object sender, EventArgs e)
        {
            tX.Value = 0;
            tY.Value = 0;
            tZ.Value = 0;

            chWires.Checked = true;
            chFront.Checked = false;
            chBack.Checked = false;
            chLeft.Checked = false;
            chRight.Checked = false;
            chTop.Checked = false;
            chBottom.Checked = false;

            mainCube = new Math3D.Cube(100, 200, 75); //Start over
            this.Refresh();
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            Render();
        }

        private void linkLabel1_LinkClicked(object sender, LinkLabelLinkClickedEventArgs e)
        {
            System.Diagnostics.Process.Start("http://vckicks.110mb.com/");
        }

        private void chWires_CheckedChanged(object sender, EventArgs e)
        {
            mainCube.DrawWires = chWires.Checked;
            this.Refresh();
        } 

        private void chFront_CheckedChanged(object sender, EventArgs e)
        {
            mainCube.FillFront = chFront.Checked;
            this.Refresh();
        }

        private void chBack_CheckedChanged(object sender, EventArgs e)
        {
            mainCube.FillBack = chBack.Checked;
            this.Refresh();
        }

        private void chLeft_CheckedChanged(object sender, EventArgs e)
        {
            mainCube.FillLeft = chLeft.Checked;
            this.Refresh();
        }

        private void chRight_CheckedChanged(object sender, EventArgs e)
        {
            mainCube.FillRight = chRight.Checked;
            this.Refresh();
        }

        private void chTop_CheckedChanged(object sender, EventArgs e)
        {
            mainCube.FillTop = chTop.Checked;
            this.Refresh();
        }

        private void chBottom_CheckedChanged(object sender, EventArgs e)
        {
            mainCube.FillBottom = chBottom.Checked;
            this.Refresh();
        }
             
    }
}