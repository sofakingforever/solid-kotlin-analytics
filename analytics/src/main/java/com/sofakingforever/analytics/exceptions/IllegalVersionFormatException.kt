package com.sofakingforever.analytics.version

import java.net.MalformedURLException

class IllegalVersionFormatException(version : String) : MalformedURLException("Version '$version' is illegal") {

}