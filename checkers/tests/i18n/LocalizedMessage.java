import checkers.i18n.quals.*;

class LocalizedMessage {
    @Localized String localize(String s) { throw new RuntimeException(); }
    void localized(@Localized String s) { }
    void any(String s) { }

    void stringLiteral() {
        //:: (type.incompatible)
        localized("ldskjfldj"); // error
        any("lksjdflkjdf");
    }

    void stringRef(String ref) {
        //:: (type.incompatible)
        localized(ref);   // error
        any(ref);
    }

    void localizedRef(@Localized String ref) {
        localized(ref);
        any(ref);
    }

    void methodRet(String ref) {
        localized(localize(ref));
        localized(localize(ref));
    }

    void concatenation(@Localized String s1, String s2) {
        //:: (type.incompatible)
        localized(s1 + s1);     // error
        //:: (type.incompatible)
        localized(s1 + "m");    // error
        //:: (type.incompatible)
        localized(s1 + s2);     // error

        //:: (type.incompatible)
        localized(s2 + s1);     // error
        //:: (type.incompatible)
        localized(s2 + "m");    // error
        //:: (type.incompatible)
        localized(s2 + s2);     // error

        any(s1 + s1);
        any(s1 + "m");
        any(s1 + s2);

        any(s2 + s1);
        any(s2 + "m");
        any(s2 + s2);

    }
}
