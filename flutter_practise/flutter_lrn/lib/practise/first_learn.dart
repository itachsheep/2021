
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
void main() {
  testString();
}