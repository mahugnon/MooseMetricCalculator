"
I'm the adapter for FAMIXClass
"
Class {
	#name : #MMCFAMIXClassAdapter,
	#superclass : #MMCAbstractAdapter,
	#category : #'Moose-Metric-Calculator-Adapter'
}

{ #category : #initialization }
MMCFAMIXClassAdapter >> initialize [
	super initialize.
	mooseEntity := FAMIXClass
]
