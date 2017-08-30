using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Net.Mail;
using System.Linq;
using System.Web;
using System.Net;
using System.IO;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;


namespace FinalApplication1
{
    public partial class Form1 : Form
    {
        public 
            Form1()
        {
            InitializeComponent();
            serialPort1.BaudRate = 9600;
            serialPort1.PortName = "COM4";
            serialPort1.Open();
            serialPort1.DtrEnable = true;
            serialPort1.DataReceived += serialPort1_DataReceived;

            //InitializeComponent();
            //serialPort2.BaudRate = 9600;
            //serialPort2.PortName = "COM3";
            //serialPort2.Open();
            //serialPort2.DtrEnable = true;
            //serialPort2.DataReceived += serialPort2_DataReceived;
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            timer1 = new Timer();
            timer1.Interval = (20 * 1000);// (2 * 60 * 1000);
            timer1.Tick += new EventHandler(Tick1);
            timer1.Start();
        }

        private void Tick1(object sender, EventArgs e)
        {
            int i, j, k;
            timer1.Stop();
            if (int.TryParse(textBox1.Text, out i) && int.TryParse(textBox2.Text, out j) && int.TryParse(textBox3.Text, out k))
            //if (int.TryParse(textBox2.Text, out i))
            {
                string s = UpdateThingSpeakData(new string[] { textBox1.Text,textBox2.Text,textBox3.Text}, "LTBZ0NE49NFKQTPN");
            }
            timer1.Start(); 
        }


        string UpdateThingSpeakData(string[] fields, string APIKey)
        {
            string url = "http://api.thingspeak.com/";
            StringBuilder sb = new StringBuilder();
            if (fields.Length > 8)
            {
                throw (new Exception("Can't Handle More than 8 Parameters"));
            }
            sb.Append(url + "update?key=" + APIKey);

            for (int i = 0; i < fields.Length; i++)//i=0
            {
                sb.Append("&field" + (i + 1) + "=" + HttpUtility.UrlEncode(fields[i]));

            }
            string QueryString = sb.ToString();
            StringBuilder sbResponse = new StringBuilder();
            byte[] buf = new byte[8192];

            // Hit the URL with the querystring and put the response in webResponse
            HttpWebRequest myRequest = (HttpWebRequest)WebRequest.Create(QueryString);
            HttpWebResponse webResponse = (HttpWebResponse)myRequest.GetResponse();
            try
            {
                Stream myResponse = webResponse.GetResponseStream();

                int count = 0;

                // Read the response buffer and return
                do
                {
                    count = myResponse.Read(buf, 0, buf.Length);
                    if (count != 0)
                    {
                        sbResponse.Append(Encoding.ASCII.GetString(buf, 0, count));
                    }
                }
                while (count > 0);
                return sbResponse.ToString();
            }
            catch (WebException ex)
            {
                return "0";
            }

        }



        private void serialPort1_DataReceived(object sender, System.IO.Ports.SerialDataReceivedEventArgs e)
        {
            string line = serialPort1.ReadLine();
            this.BeginInvoke(new LineReceivedEvent(LineReceived), line);

        }


     //  private void serialPort2_DataReceived(object sender, System.IO.Ports.SerialDataReceivedEventArgs e)//
    //    {
      //      string line1 = serialPort2.ReadLine();
        //    this.BeginInvoke(new LineReceivedEvent1(LineReceived1), line1);

//        }

        private delegate void LineReceivedEvent(string line);


  //      private delegate void LineReceivedEvent1(string line1);

        private void LineReceived(string line)
        {
            
            //textBox1.Text = line;
            String[] values=line.Split('\t');
            textBox1.Text = values[0];
            textBox2.Text = values[1];
            textBox3.Text = values[2];
        }


//        private void LineReceived1(string line1)
       // {

            //textBox2.Text = line1;
           // textBox2.Text = line1;
        //}


        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (serialPort1.IsOpen) serialPort1.Close();
          //  if (serialPort2.IsOpen) serialPort2.Close();

        }

        private void serialPort1_ErrorReceived(object sender, System.IO.Ports.SerialErrorReceivedEventArgs e)
        {
            serialPort1.Close();
           // serialPort2.Close();

        }


        private void Tick(object sender, EventArgs e)
        {
            int j;

            int.TryParse(textBox1.Text, out j);
            if (j < 35)
            {
                timer.Stop();
            }

            else
            {
                timer.Stop();

                Task sendEmail = new Task(() =>
                {
                    //create the mail message
                    MailMessage mail = new MailMessage();

                    //set the addresses
                    mail.From = new MailAddress("siddhitheonly1@gmail.com");
                    mail.To.Add("siddhitheonly1@gmail.com");

                    //set the content
                    mail.Subject = "This is an email";
                    mail.Body = "this is a sample body";

                    //send the message
                    SmtpClient smtp = new SmtpClient();
                    smtp.Port = 587;
                    smtp.UseDefaultCredentials = true;

                    smtp.Host = "smtp.gmail.com";

                    smtp.EnableSsl = true;
                    smtp.Credentials = new System.Net.NetworkCredential("siddhitheonly1@gmail.com", "ganapatibappa");

                    smtp.Send(mail);
                });

                sendEmail.Start();

                //mail();              
            }

        }

        private bool quit = false;
       

        private void textBox1_TextChanged_1(object sender, EventArgs e)
        {
            timer = new Timer();
            timer.Interval = 30000;
            timer.Tick += new EventHandler(Tick);

            int i;

            int.TryParse(textBox1.Text, out i);
            if (i > 35)
            {
                timer.Start();
            }
        
        }

    }
}