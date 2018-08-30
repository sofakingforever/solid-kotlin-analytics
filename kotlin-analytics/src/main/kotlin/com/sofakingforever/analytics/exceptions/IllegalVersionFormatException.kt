package com.sofakingforever.analytics.exceptions

import java.net.MalformedURLException

class IllegalVersionFormatException(version : String) : MalformedURLException("Version '$version' is illegal") {

}