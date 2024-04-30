namespace CurrencyConverter
{
    partial class Converter
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.PeriodCB = new System.Windows.Forms.ComboBox();
            this.uSDExchangeRateBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.periodBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.FromValue = new System.Windows.Forms.NumericUpDown();
            this.comboBox2 = new System.Windows.Forms.ComboBox();
            this.fromBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.FromLabel = new System.Windows.Forms.Label();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.ToValue = new System.Windows.Forms.TextBox();
            this.ToCB = new System.Windows.Forms.ComboBox();
            this.toBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.ToLabel = new System.Windows.Forms.Label();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            ((System.ComponentModel.ISupportInitialize)(this.uSDExchangeRateBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.periodBindingSource)).BeginInit();
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.FromValue)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.fromBindingSource)).BeginInit();
            this.groupBox2.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.toBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(210, 34);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(238, 29);
            this.label1.TabIndex = 0;
            this.label1.Text = "Currency Converter";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(44, 104);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(47, 16);
            this.label2.TabIndex = 1;
            this.label2.Text = "Period";
            // 
            // PeriodCB
            // 
            this.PeriodCB.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", this.uSDExchangeRateBindingSource, "period_id", true));
            this.PeriodCB.DataSource = this.periodBindingSource;
            this.PeriodCB.DisplayMember = "name";
            this.PeriodCB.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.PeriodCB.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.PeriodCB.FormattingEnabled = true;
            this.PeriodCB.Location = new System.Drawing.Point(118, 104);
            this.PeriodCB.Name = "PeriodCB";
            this.PeriodCB.Size = new System.Drawing.Size(505, 24);
            this.PeriodCB.TabIndex = 2;
            this.PeriodCB.ValueMember = "id";
            this.PeriodCB.SelectedIndexChanged += new System.EventHandler(this.PeriodCB_SelectedIndexChanged);
            // 
            // uSDExchangeRateBindingSource
            // 
            this.uSDExchangeRateBindingSource.DataSource = typeof(CurrencyConverter.USDExchangeRate);
            // 
            // periodBindingSource
            // 
            this.periodBindingSource.DataSource = typeof(CurrencyConverter.Period);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.FromValue);
            this.groupBox1.Controls.Add(this.comboBox2);
            this.groupBox1.Controls.Add(this.FromLabel);
            this.groupBox1.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.groupBox1.Location = new System.Drawing.Point(47, 163);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(244, 113);
            this.groupBox1.TabIndex = 3;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Origin Ammount:";
            // 
            // FromValue
            // 
            this.FromValue.Location = new System.Drawing.Point(15, 64);
            this.FromValue.Maximum = new decimal(new int[] {
            -1304428545,
            434162106,
            542,
            0});
            this.FromValue.Name = "FromValue";
            this.FromValue.Size = new System.Drawing.Size(135, 22);
            this.FromValue.TabIndex = 5;
            this.FromValue.ValueChanged += new System.EventHandler(this.FromValue_ValueChanged);
            // 
            // comboBox2
            // 
            this.comboBox2.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", this.uSDExchangeRateBindingSource, "currency_id", true));
            this.comboBox2.DataSource = this.fromBindingSource;
            this.comboBox2.DisplayMember = "abbreviation";
            this.comboBox2.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.comboBox2.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.comboBox2.FormattingEnabled = true;
            this.comboBox2.Location = new System.Drawing.Point(166, 62);
            this.comboBox2.Name = "comboBox2";
            this.comboBox2.Size = new System.Drawing.Size(67, 24);
            this.comboBox2.TabIndex = 4;
            this.comboBox2.ValueMember = "id";
            this.comboBox2.SelectedIndexChanged += new System.EventHandler(this.comboBox2_SelectedIndexChanged);
            // 
            // fromBindingSource
            // 
            this.fromBindingSource.DataSource = typeof(CurrencyConverter.Currency);
            // 
            // FromLabel
            // 
            this.FromLabel.AutoSize = true;
            this.FromLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.FromLabel.Location = new System.Drawing.Point(12, 34);
            this.FromLabel.Name = "FromLabel";
            this.FromLabel.Size = new System.Drawing.Size(38, 16);
            this.FromLabel.TabIndex = 4;
            this.FromLabel.Text = "From";
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.ToValue);
            this.groupBox2.Controls.Add(this.ToCB);
            this.groupBox2.Controls.Add(this.ToLabel);
            this.groupBox2.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.groupBox2.Location = new System.Drawing.Point(379, 163);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(244, 113);
            this.groupBox2.TabIndex = 4;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Converted To:";
            // 
            // ToValue
            // 
            this.ToValue.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.ToValue.Enabled = false;
            this.ToValue.Location = new System.Drawing.Point(15, 62);
            this.ToValue.Name = "ToValue";
            this.ToValue.Size = new System.Drawing.Size(134, 22);
            this.ToValue.TabIndex = 5;
            // 
            // ToCB
            // 
            this.ToCB.DataBindings.Add(new System.Windows.Forms.Binding("SelectedValue", this.uSDExchangeRateBindingSource, "currency_id", true));
            this.ToCB.DataSource = this.toBindingSource;
            this.ToCB.DisplayMember = "abbreviation";
            this.ToCB.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.ToCB.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ToCB.FormattingEnabled = true;
            this.ToCB.Location = new System.Drawing.Point(166, 62);
            this.ToCB.Name = "ToCB";
            this.ToCB.Size = new System.Drawing.Size(67, 24);
            this.ToCB.TabIndex = 4;
            this.ToCB.ValueMember = "id";
            this.ToCB.SelectedIndexChanged += new System.EventHandler(this.ToCB_SelectedIndexChanged);
            // 
            // toBindingSource
            // 
            this.toBindingSource.DataSource = typeof(CurrencyConverter.Currency);
            // 
            // ToLabel
            // 
            this.ToLabel.AutoSize = true;
            this.ToLabel.Font = new System.Drawing.Font("Microsoft Sans Serif", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ToLabel.Location = new System.Drawing.Point(12, 34);
            this.ToLabel.Name = "ToLabel";
            this.ToLabel.Size = new System.Drawing.Size(24, 16);
            this.ToLabel.TabIndex = 4;
            this.ToLabel.Text = "To";
            // 
            // pictureBox1
            // 
            this.pictureBox1.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pictureBox1.Image = global::CurrencyConverter.Properties.Resources.exchange;
            this.pictureBox1.Location = new System.Drawing.Point(308, 203);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(51, 35);
            this.pictureBox1.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pictureBox1.TabIndex = 5;
            this.pictureBox1.TabStop = false;
            this.pictureBox1.Click += new System.EventHandler(this.pictureBox1_Click);
            // 
            // Converter
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(676, 314);
            this.Controls.Add(this.pictureBox1);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.PeriodCB);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Converter";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Currency Converter";
            this.Load += new System.EventHandler(this.Converter_Load);
            ((System.ComponentModel.ISupportInitialize)(this.uSDExchangeRateBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.periodBindingSource)).EndInit();
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.FromValue)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.fromBindingSource)).EndInit();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.toBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ComboBox PeriodCB;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.NumericUpDown FromValue;
        private System.Windows.Forms.ComboBox comboBox2;
        private System.Windows.Forms.Label FromLabel;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.TextBox ToValue;
        private System.Windows.Forms.ComboBox ToCB;
        private System.Windows.Forms.Label ToLabel;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.BindingSource uSDExchangeRateBindingSource;
        private System.Windows.Forms.BindingSource periodBindingSource;
        private System.Windows.Forms.BindingSource fromBindingSource;
        private System.Windows.Forms.BindingSource toBindingSource;
    }
}

