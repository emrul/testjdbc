package com.vividinventive.graalmods;

import com.oracle.svm.core.annotate.AutomaticFeature;
import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import org.graalvm.nativeimage.Feature;
import org.graalvm.nativeimage.RuntimeReflection;
import org.postgresql.core.PGStream;
import org.postgresql.sspi.ISSPIClient;

/**
 * Created by emrul on 2019-03-02.
 *
 * @author Emrul Islam <emrul@emrul.com>
 * Copyright 2014 Emrul Islam
 */

@AutomaticFeature
class RuntimeReflectionRegistrationFeature implements Feature {
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        try {
            RuntimeReflection.register(java.sql.Statement[].class);
        } catch (Exception e) {

        }
    }
}

@TargetClass(org.postgresql.core.v3.ConnectionFactoryImpl.class)
final class substrate_pg {
    @Substitute
    private ISSPIClient createSSPI(PGStream pgStream, String spnServiceClass, boolean enableNegotiate) {
        throw new IllegalStateException("Not supported under graal");
    }
}
