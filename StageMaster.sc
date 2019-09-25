StageMaster
{
	classvar masterSynth, masterFunc, activeSynth;
	
	*activate
	{ |numChannels = 2, compThreshold = 0.7, limiterLevel = 1.0, highEndDb = 3.0|
		fork
		{
			masterFunc = 
			{ 
				{ 
					activeSynth = 
						Synth(\stageMaster,
							target: RootNode(Server.default), 
							addAction: \addToTail
						);
				}.defer(0.01) 
			};

			masterSynth = SynthDef(
                                \stageMaster, 
                                {
                                        var input = In.ar(0, numChannels);

                                        // Remove DC offset
                                        input = Select.ar(CheckBadValues.ar(input, 0, 0), [input, DC.ar(0), DC.ar(0), input]);

                                        // Compressor
                                        input = Compander.ar(
                                                input,
                                                input,
                                                thresh: compThreshold,
                                                slopeBelow: 1.05,
                                                slopeAbove: 0.4,
                                                clampTime:  0.01,
                                                relaxTime:  0.01
                                        );

                                        // Hi-shelf EQ, for brightness
                                        input = BHiShelf.ar(
                                                input,
                                                freq: 2400.0,
                                                rs: 1.0,
                                                db: highEndDb
                                        );

                                        // Limiter
                                        input = Limiter.ar(input, limiterLevel);
                                        ReplaceOut.ar(0, input);
                                }
                        ).add;

			Server.default.sync;
			masterFunc.value;
			CmdPeriod.add(masterFunc);
			"StageMaster activated".postln;
		}
	}
	
	*deactivate
	{
		activeSynth.free;
		CmdPeriod.remove(masterFunc);
		"StageMaster inactive...".postln;
	}
}
