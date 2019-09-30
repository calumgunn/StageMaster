# StageMaster

StageMaster is a light mastering chain for use during live performance in SuperCollider.

It adds light compression, EQ and limiting to all output. Current defaults are tuned to the author's
tastes.

Based on the excellent StageLimiter by Batuhan Bozkurt.

## Installation

In SuperCollider, evaluate the following code to install it as a quark:
```
Quarks.install("https://github.com/calumgunn/StageMaster.git");
```


## Usage

```
StageMaster.activate(numChannels: 2, compThreshold: 0.7, limiterLevel: 1.0, highEndDb: 3.0);

StageMaster.deactivate;
```
