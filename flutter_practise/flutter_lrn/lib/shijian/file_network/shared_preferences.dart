
import 'package:shared_preferences/shared_preferences.dart';

class SharedPreferencesUtils {
  static void putString(String key, String value) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.setString(key, value);
  }

  static void removeString(String key) async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.remove(key);
  }

}