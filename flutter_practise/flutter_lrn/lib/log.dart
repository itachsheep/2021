
class LogUtils {
  static const String TAG = "FlutterLrn.";
  static void d(String tag, String msg) {
    print(TAG + tag + ": " + msg);
  }

  static void dd(String msg, {String tag = ""}) {
    print(TAG + tag + ": " + msg);
  }


}