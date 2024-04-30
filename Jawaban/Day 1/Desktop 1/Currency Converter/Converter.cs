using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CurrencyConverter
{
    public partial class Converter : Form
    {
        CurrencyConverterEntities db = new CurrencyConverterEntities();

        public Converter()
        {
            InitializeComponent();
        }

        private void GetFromCountryName()
        {
            var countryId = (int)comboBox2.SelectedValue;
            var countryName = db.Currency.FirstOrDefault(c => c.id == countryId).name;

            FromLabel.Text = countryName;
        }

        private void GoToCountryName()
        {
            var countryId = (int)ToCB.SelectedValue;
            var countryName = db.Currency.FirstOrDefault(c => c.id == countryId).name;

            ToLabel.Text = countryName;
        }

        private void GetToValue()
        {
            var value = FromValue.Value;
            var countryId = (int)comboBox2.SelectedValue;
            var toCountryId = (int)ToCB.SelectedValue;
            var date = (int)PeriodCB.SelectedValue;

            if (countryId == 28 && toCountryId == 28)
            {
                ToValue.Text = value.ToString("0.###");
            }
            else
            {
                if (toCountryId == 28)
                {
                    var FromRate = db.USDExchangeRate.FirstOrDefault(c => c.currency_id == countryId && c.period_id == date).rate;

                    var USDRate = value / FromRate;
                    ToValue.Text = USDRate.ToString("0.###");
                }
                else
                {
                    var ToRate = db.USDExchangeRate.FirstOrDefault(c => c.currency_id == toCountryId && c.period_id == date).rate;

                    if (countryId == 28)
                    {
                        var USDRate = value * ToRate;
                        ToValue.Text = USDRate.ToString("0.###");
                    }
                    else
                    {
                        var FromRate = db.USDExchangeRate.FirstOrDefault(c => c.currency_id == countryId && c.period_id == date).rate;
                        var cross = value / FromRate * ToRate;
                        ToValue.Text = cross.ToString("0.###");
                    }
                }
            }
        }

        private void SwitchCurrency()
        {
            var countryId = (int)comboBox2.SelectedValue;
            var toCountryId = (int)ToCB.SelectedValue;

            comboBox2.SelectedValue = toCountryId;
            ToCB.SelectedValue = countryId;
        }

        private void Converter_Load(object sender, EventArgs e)
        {
            periodBindingSource.Clear();
            periodBindingSource.DataSource = db.Period.ToList();

            fromBindingSource.Clear();
            fromBindingSource.DataSource = db.Currency.ToList();

            toBindingSource.Clear();
            toBindingSource.DataSource = db.Currency.ToList();

            GetFromCountryName();
            GoToCountryName();
            GetToValue();
        }

        private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
        {
            GetFromCountryName();
            GetToValue();
        }

        private void ToCB_SelectedIndexChanged(object sender, EventArgs e)
        {
            GoToCountryName();
            GetToValue();
        }

        private void FromValue_ValueChanged(object sender, EventArgs e)
        {
            GetToValue();
        }

        private void PeriodCB_SelectedIndexChanged(object sender, EventArgs e)
        {
            GetToValue();
        }

        private void pictureBox1_Click(object sender, EventArgs e)
        {
            SwitchCurrency();
            GetToValue();
        }
    }
}
