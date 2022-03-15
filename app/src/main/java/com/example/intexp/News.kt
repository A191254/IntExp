package com.example.intexp

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.intexp.databinding.ActivityNewsBinding
import java.util.ArrayList

class News : AppCompatActivity(), SchemeItemClicked {
    private  lateinit var binding: ActivityNewsBinding
    private lateinit var myadapter: MyAdapterNews
    var str: String? = null
    var b1: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fetchData()
        myadapter = MyAdapterNews( this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter= myadapter
        //mySearchView = findViewById(R.id.filter);
        val search = findViewById<SearchView>(R.id.filter)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

                b1=false
                if(query=="")
                {
                    b1=true
                }
                str = "+\"$query\""
                fetchData()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                str = "+\"$newText\""
                if(newText=="")
                {
                    b1=true
                }
                fetchData()
                return true
            }

        })
    }
    private fun fetchData() {
        val url = "https://newsapi.org/v2/everything?q=\"software\"+\"jobs\"$str&apiKey=c2703a6484d640bfa98398c825826061"
        val jsonObjectRequest = object: JsonObjectRequest(
            Request.Method.GET,
            url,
            null,
            Response.Listener {
                val newsJsonArray = it.getJSONArray("articles")
                val newsArray = ArrayList<News1>()

                val newsi = News1(
                    "Software Engineering - Python",
                    "JPMC",
                    "https://jpmc.fa.oraclecloud.com/hcmUI/CandidateExperience/en/sites/CX_1001/job/210254728/?utm_medium=jobshare&src=LinkedIn_JPMC",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e0/JPMorgan_Chase.svg/1280px-JPMorgan_Chase.svg.png",
                    "Candidate will be expected to understand the Markets Business and work on " +
                            "flagship product Athena and its Ecosystem gaining expertise to various in-house Athena core frameworks. Candidates will have opportunity to contribute across the whole Tech Stack," +
                            " Front to Back across Regulatory requirements, Global Middle Office requirements," +
                            " and engineering driven improvements to the Athena Ecosystem for whole spectrum of Markets products."

                )
                val newsj = News1(
                    "Security Analyst - Python",
                    "Akamai",
                "https://akamaijobs.referrals.selectminds.com/jobs/security-analyst-python-17716",
                    "https://upload.wikimedia.org/wikipedia/commons/8/8b/Akamai_logo.svg",
                "Our team is part of the Security organization, responsible for developing products and platforms focused on security. We build unique technology that can secure the cloud and make it a safe place to do billions of transactions and handle petabytes of data every day.\n" +
                        "Help shape the future of the Internet\n" +
                        "The primary focus is to analyze online fraudulent activity in order to derive actionable insights. Work with real time systems, machine learning models and manipulate huge datasets. Apply your analysis skills to solve customer escalations with a sense of urgency. " +
                        "Also work closely with Software Developers, Data Scientists and Product Managers to continuously improve our fraud detection capabilities\n",

                )
                val newsk = News1(
                    "Support Engineer",
                    "Salesforce",
                    "https://salesforce.wd1.myworkdayjobs.com/en-US/External_Career_Site/job/India---Hyderabad/Technical-Support-Engineer_JR142414?source=LinkedIn_Jobs\n",
                "https://www.google.com/imgres?imgurl=https%3A%2F%2Fupload.wikimedia.org%2Fwikipedia%2Fcommons%2Fthumb%2Ff%2Ff9%2FSalesforce.com_logo.svg%2F1200px-Salesforce.com_logo.svg.png&imgrefurl=https%3A%2F%2Fen.wikipedia.org%2Fwiki%2FSalesforce&tbnid=O08jFMBtMATpMM&vet=12ahUKEwiRpvzi9cj2AhUzhNgFHZnmAS0QMygBegUIARDfAQ..i&docid=y_d4lt7RWEQ09M&w=1200&h=840&q=salesforce&ved=2ahUKEwiRpvzi9cj2AhUzhNgFHZnmAS0QMygBegUIARDfAQ",
                    "Salesforce.com is the global leader in customer relationship management (CRM) software. We pioneered the shift to cloud computing, and today we're delivering the next generation of social, mobile and cloud technologies that help companies revolutionize the way they sell, service, market and innovate-and become customer companies. We are the fastest growing of the top 10 enterprise software companies, the World's Most Innovative Company according to Forbes and one of Fortune's 100 Best Companies to Work For. Our \"more human, less corporate\" culture is built around doing work that matters, winning as a team and celebrating success. "
                )

                val news10 = News1(
                    "Project Intern-Electrical ", "Philips", "https://www.careers.philips.com/student/global/en/job/433759/Project-Intern-Electrical-STET?utm_campaign=google_jobs_apply&utm_source=google_jobs_apply&utm_medium=organic",
                    "https://cdn.phenompeople.com/CareerConnectResources/PHILUS/en_global/desktop/assets/images/v-1623510417148-philips_blue.svg",
                    "Job Title\n" +
                            "Project Intern-Electrical-STET\n" +
                            "\n" +
                            "Job Description\n" +
                            "\n" +
                            "In this role, you have the opportunity to\n" +
                            "\n" +
                            "Manage high impact, complex transfer projects that enables " +
                            "Philips to reduce risk, improve profitability," +
                            " align with Philips’ strategic supply base and optimize supply chain flexibility. " +
                            "The primary function within this role is to execute " +
                            "engineering activities through the course of fast-paced, high quality transfer projects."
                )
                val news20 = News1(
                    "Web Development Internship in Pune at Internship Studio", "Intership Studio", "https://www.linkedin.com/jobs/view/web-development-internship-in-pune-at-internship-studio-at-internship-studio-2893173384?utm_campaign=google_jobs_apply&utm_source=google_jobs_apply&utm_medium=organic&originalSubdomain=in",
                    "https://media-exp1.licdn.com/dms/image/C4E0BAQEGw09XmbKg3A/company-logo_200_200/0/1614678631807?e=1651104000&v=beta&t=2j8M0f9c0tiw7Qfjgp4sj_u3dC4DHwghPiIohvU_qS8", "TWebsite\n" +
                            "\n" +
                            "We provide upskilling opportunities to young students with a vision to bridge the gap between academia and industry. Our outcome-based experiential learning programs on emerging technologies are one of a kind.\n" +
                            "\n" +
                            "Activity on Internshala\n" +
                            "\n" +
                            "Hiring since May 2021\n" +
                            "\n" +
                            "49 opportunities posted\n" +
                            "\n" +
                            "14 candidates hired\n" +
                            "\n" +
                            "About The Internship\n" +
                            "\n" +
                            "Web Developer intern's day-to-day responsibilities include:\n" +
                            "• Working in PHP, WordPress, MySQL, REST APIs, etc.\n" +
                            "• Maintaining the organization's websites\n" +
                            "• Removing the defects from the websites if any\n" +
                            "• Implementing new features on the websites\n" +
                            "• Handling the security and page speed of the websites\n" +
                            "• Giving constructive ideas to make websites easy to use"
                )
                val news30 = News1(
                    "Merkle Sokrati Internship 2022: Hiring Data Analytics at Pune", "Merkle Sokrati", "https://questionpapershub.com/free-job-alert/merkle-sokrati-internship-2022-hiring-data-analytics-interns-of-any-graduate-degree/?utm_campaign=google_jobs_apply&utm_source=google_jobs_apply&utm_medium=organic" +
                            "personal-finance/news-pm-kisan-what-is-kisan-credit-card-how-to-apply-know-step-" +
                            "by-step-process-to-apply-kcc-online-through-sbi-162498", "https://questionpapershub.com/free-job-alert/wp-content/uploads/2022/01/merkle-internship-2022.jpg?ezimgfmt=ng:webp/ngcb49", "Merkle Sokrati Internship 2022 Notification has been published. Freshers Candidates of Any Degree Are Eligible to Apply this Drive\n" +
                            "\n" +
                            "Notification: Merkle Sokrati Internship 2022\n" +
                            "Company Name: Merkle Sokrati\n" +
                            "Job Role: Data Analytics Intern\n" +
                            "Experience: Freshers\n" +
                            "Job Type: Internship\n" +
                            "Salary: Not Disclosed\n" +
                            "Job Location: Pune, Maharashtra, India"
                )
                if(b1) {
                    newsArray.add(newsi)
                    newsArray.add(newsj)
                    newsArray.add(newsk)
                    newsArray.add(news10)
                    newsArray.add(news20)
                    newsArray.add(news30)
                }
                for(i in 0 until newsJsonArray.length()) {
                    val newsJSONObject = newsJsonArray.getJSONObject(i)
                    val news = News1(
                        newsJSONObject.getString("title"),
                        newsJSONObject.getString("author"),
                        newsJSONObject.getString("url"),
                        newsJSONObject.getString("urlToImage"),
                        newsJSONObject.getString("content")
                    )
                    newsArray.add(news)
                }
                if(!b1) {
                    newsArray.add(newsi)
                    newsArray.add(newsj)
                    newsArray.add(newsk)
                    newsArray.add(news10)
                    newsArray.add(news20)
                    newsArray.add(news30)
                }
                myadapter.updateNews(newsArray)
            },
            Response.ErrorListener {

            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: News1) {
        //Toast.makeText(this,"clicked item is ${item.title}", Toast.LENGTH_LONG).show()
        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}
class MySingleton constructor(context: Context){
    companion object {
        @Volatile
        private var INSTANCE: MySingleton? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: MySingleton(context).also {
                    INSTANCE = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }
}
data class News1(val title: String, val author: String, val url: String, val imageUrl: String, val content: String)