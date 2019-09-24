# StageMaster

StageMaster is a light mastering chain for use during live performance in SuperCollider.

It adds light compression, EQ and limiting to all output. Current defaults are tuned to the author's
tastes.

Based on the excellent StageLimiter by Batuhan Bozkurt.

## Usage

```
StageMaster.activate(numChannels: 2, compThreshold: 0.7, limiterLevel: 1.0, highEndDb: 3.0);

StageMaster.deactivate;
```
