module namespace xxx = "xxx";
import module namespace yyy = "yyy" at "yyy";
import module "aaa" at "aaa";
declare namespace zzz = "zzz";

declare function xxx:xxx() {
    <abc xmlns:xxx="abc">
        <xxx:any x<caret>xx:any="val"/>
    </abc>
};