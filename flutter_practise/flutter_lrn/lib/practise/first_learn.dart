
void myPrint(dynamic msg) {
   // ignore: avoid_print
   print(" $msg");
}

void testConst() {
  final bar = const [];
  const baz = [];

}

void testNumber() {
  var one = int.parse('1');
  assert(one == 1);

  String oneString = 1.toString();
  assert(oneString == '1');

  myPrint("no error");
}

void testString() {
  var s = "string interpolation";
  final ss = s + " for all love";
  myPrint(s + " -> " + ss);


  // const 类型数据
  const aConstNum = 0;
  const aConstBool = true;
  const aConstString = 'a constant string';

  var aNum = 0;
  var aBool = true;
  var aString = 'a string';
  const aConstList = [1, 2, 3];

  const validConstString = '$aConstNum $aConstBool $aConstString';
  var invalidConstString = '$aNum $aBool $aString $aConstList';

  myPrint("validConstString = $validConstString");
  myPrint("invalidConstString = $invalidConstString");
}

void testList() {
  var list = [1, 2, 3];
  myPrint("list 1: ${list[1]}");
  list.forEach((element) {
    myPrint("ele: $element");
  });
}

void testSet() {
  var haloSet = {"dd", "ww", "22"};
  haloSet.add("taowei");

  var nameSet = <String> {};
  nameSet.add("Jack");

  Set<String> siSet = {};
  siSet.add("ha");

  var mMap = {};

  const conSet = {
    "ttt",
    "ha",
    "ddd",
  };

  var mySet = <String>{};
  mySet.addAll(conSet);

  myPrint("mySet: $mySet");
}

testMap() {
  var gitfs = {
    "key1":"val1",
    "key2":"val2"
  };
  gitfs["key3"] = "val3";

  myPrint("gifts: $gitfs");

  var nMap = {};
  nMap[1] = "val1";
  nMap["222"] = "val2";
  nMap[3] = 3;
  myPrint("nMap = $nMap");
}


testRune() {
  var clapping = '\u{1f44f}';
  myPrint(clapping);
  myPrint(clapping.codeUnits);
  myPrint(clapping.runes.toList());

  Runes input = new Runes(
      '\u2665  \u{1f605}  \u{1f60e}  \u{1f47b}  \u{1f596}  \u{1f44d}');
  myPrint(new String.fromCharCodes(input));
}
void main() {
  testRune();
}