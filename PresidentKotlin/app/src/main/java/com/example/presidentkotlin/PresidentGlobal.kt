package com.example.presidentkotlin

import java.util.ArrayList

/**
 * Created by HimelR on 08-Feb-18.
 */
class PresidentsGlobal private constructor() {
    val presidents: ArrayList<President> = ArrayList()

    fun addPresident(p: President) {
        presidents.add(p)
    }

    companion object {

        var instance: PresidentsGlobal? = null
            private set

        init {
            try {
                instance = PresidentsGlobal()
                addPresidents()
            } catch (e: Exception) {
                throw RuntimeException("Error occurred")
            }

        }

        private fun addPresidents() {


            if (PresidentsGlobal.instance!!.presidents.isEmpty()) {
                PresidentsGlobal.instance!!.addPresident(President("Ståhlberg, Kaarlo Juho", 1919, 1925, "http://www.presidentti.fi/public/download.aspx?ID=44362&GUID={0FC2CA27-5FB2-4F3E-BB29-D9B9CE8EFCA5}&maxwidth=565&maxheight=500"))
                PresidentsGlobal.instance!!.addPresident(President("Relander, Lauri Kristian", 1925, 1931, "http://www.presidentti.fi/public/download.aspx?ID=44363&GUID={4C489246-AE16-4068-A52F-C1F9A1B9D2D5}&maxwidth=565&maxheight=500"))
                PresidentsGlobal.instance!!.addPresident(President("Svinhufvud, Pehr Evind", 1931, 1937, "http://www.presidentti.fi/public/download.aspx?ID=44364&GUID={6FE9116A-CAE0-4DE2-B5FC-6D2B326F4B96}&maxwidth=565&maxheight=500"))
                PresidentsGlobal.instance!!.addPresident(President("Kallio, Kyösti", 1937, 1940, "http://www.presidentti.fi/public/download.aspx?ID=44365&GUID={634A2595-5A0D-4EA0-A38F-DD2E86F65C07}&maxwidth=565&maxheight=500"))
                PresidentsGlobal.instance!!.addPresident(President("Ryti, Risto Heikki", 1940, 1944, "http://www.presidentti.fi/public/download.aspx?ID=44366&GUID={0573E1B8-C894-45D2-9198-96EF1A760D68}&maxwidth=565&maxheight=500"))
                PresidentsGlobal.instance!!.addPresident(President("Mannerheim, Carl Gustaf Emil", 1944, 1946, "http://www.presidentti.fi/public/download.aspx?ID=44367&GUID={E1184C16-23D4-4DD9-B103-C1C80EFFDE11}&maxwidth=565&maxheight=500"))
                PresidentsGlobal.instance!!.addPresident(President("Paasikivi, Juho Kusti", 1946, 1956, "http://www.presidentti.fi/public/download.aspx?ID=44368&GUID={5F3374D7-CDCD-47B7-8D7C-846D103260D4}&maxwidth=565&maxheight=500"))
                PresidentsGlobal.instance!!.addPresident(President("Kekkonen, Urho Kaleva", 1956, 1982, "http://www.presidentti.fi/public/download.aspx?ID=44369&GUID={1F9BAFE2-0F66-4742-8ABD-E1C37513926B}&maxwidth=565&maxheight=500"))
                PresidentsGlobal.instance!!.addPresident(President("Koivisto, Mauno Henrik", 1982, 1994, "http://www.presidentti.fi/public/download.aspx?ID=44370&GUID={3A6C9131-7E07-438A-8859-981D9B3AE5F6}&maxwidth=565&maxheight=500"))
                PresidentsGlobal.instance!!.addPresident(President("Ahtisaari, Martti Oiva Kalevi", 1994, 2000, "http://www.presidentti.fi/public/download.aspx?ID=44371&GUID={C883942D-35AE-4604-BD79-9116CCBD9A16}&maxwidth=565&maxheight=500"))
                PresidentsGlobal.instance!!.addPresident(President("Halonen, Tarja Kaarina", 2000, 2012, "http://www.presidentti.fi/public/download.aspx?id=91187&guid={0DB229FB-8420-4E97-9682-6F33F5750C39}&maxwidth=565&maxheight=500"))
            }


        }
    }
}
