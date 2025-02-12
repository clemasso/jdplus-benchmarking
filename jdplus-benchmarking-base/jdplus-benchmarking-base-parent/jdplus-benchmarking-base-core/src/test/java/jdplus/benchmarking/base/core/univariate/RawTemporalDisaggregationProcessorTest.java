/*
 * Copyright 2025 JDemetra+.
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 *      https://joinup.ec.europa.eu/software/page/eupl
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 */
package jdplus.benchmarking.base.core.univariate;

import jdplus.benchmarking.base.api.univariate.RawTemporalDisaggregationSpec;
import jdplus.toolkit.base.api.data.AggregationType;
import jdplus.toolkit.base.api.data.DoubleSeq;
import jdplus.toolkit.base.api.data.Parameter;
import jdplus.toolkit.base.api.ssf.SsfInitialization;
import jdplus.toolkit.base.core.math.matrices.FastMatrix;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import tck.demetra.data.Data;

/**
 *
 * @author Jean Palate
 */
public class RawTemporalDisaggregationProcessorTest {
    
    public RawTemporalDisaggregationProcessorTest() {
    }

    @Test
    public void testChowLin() {
        DoubleSeq y=DoubleSeq.of(Data.PCRA).extend(1,0);
        DoubleSeq q = DoubleSeq.of(Data.IND_PCR);
        RawTemporalDisaggregationSpec spec1 = RawTemporalDisaggregationSpec.builder()
                .disaggregationRatio(4)
                .aggregationType(AggregationType.Average)
                .residualsModel(RawTemporalDisaggregationSpec.Model.Ar1)
                //                .diffuseRegressors(true)
                .constant(true)
                .fast(false)
                .estimationPrecision(1e-9)
                .rescale(false)
                .algorithm(SsfInitialization.Augmented)
                .build();
        FastMatrix X=FastMatrix.make(q.length(), 1);
        X.column(0).copy(q);
        RawTemporalDisaggregationResults rslt1 = RawTemporalDisaggregationProcessor.process(y, X, spec1);
//        System.out.println(rslt1.getDisaggregatedSeries());
//        System.out.println(rslt1.getStdevDisaggregatedSeries());
        
        RawTemporalDisaggregationSpec spec2 = RawTemporalDisaggregationSpec.builder()
                .disaggregationRatio(4)
                .aggregationType(AggregationType.Average)
                .residualsModel(RawTemporalDisaggregationSpec.Model.Ar1)
                //                .diffuseRegressors(true)
                .constant(true)
                .fast(false)
                .parameter(Parameter.fixed(0.9))
                .estimationPrecision(1e-9)
                .rescale(false)
                .algorithm(SsfInitialization.SqrtDiffuse)
                .build();
        RawTemporalDisaggregationResults rslt2 = RawTemporalDisaggregationProcessor.process(y, X, spec2);
//        System.out.println(rslt2.getDisaggregatedSeries());
//        System.out.println(rslt2.getStdevDisaggregatedSeries());
//        assertTrue(rslt1.getStdevDisaggregatedSeries().distance(rslt2.getStdevDisaggregatedSeries()) < 1e-5);
//        assertTrue(rslt1.getStdevDisaggregatedSeries().distance(rslt2.getStdevDisaggregatedSeries()) < 1e-5);
        RawTemporalDisaggregationSpec spec3 = RawTemporalDisaggregationSpec.builder()
                .disaggregationRatio(4)
                .aggregationType(AggregationType.Average)
                .residualsModel(RawTemporalDisaggregationSpec.Model.Ar1)
                //                .diffuseRegressors(true)
                .constant(true)
                .fast(true)
                .estimationPrecision(1e-9)
                .rescale(false)
                .algorithm(SsfInitialization.Diffuse)
                .build();
        RawTemporalDisaggregationResults rslt3 = RawTemporalDisaggregationProcessor.process(y, X, spec3);
        double d=rslt1.getCoefficients().distance(rslt3.getCoefficients());
        assertTrue(d < 1e-6);
        d=rslt1.getCoefficientsCovariance().diagonal()
                .distance(rslt3.getCoefficientsCovariance().diagonal());
        assertTrue(d < 1e-6);
//        System.out.println("CL");
//        System.out.println(rslt2.getDisaggregatedSeries());
//        System.out.println(rslt2.getStdevDisaggregatedSeries().getValues());
//        System.out.println(rslt2.getCoefficients());
//        System.out.println(rslt1.getMaximum().getHessian());
//        System.out.println(rslt2.getConcentratedLikelihood().e());
//        System.out.println(rslt2.getConcentratedLikelihood().logLikelihood());
    }
    
}
